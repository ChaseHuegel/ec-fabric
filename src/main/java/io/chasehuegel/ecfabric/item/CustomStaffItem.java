package io.chasehuegel.ecfabric.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.particle.ParticleTypes;
import org.apache.commons.lang3.reflect.FieldUtils;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.magic.Spell;
import io.chasehuegel.ecfabric.magic.SpellManager;
import io.chasehuegel.ecfabric.magic.SpellScheduler;
import io.chasehuegel.ecfabric.magic.SpellType;
import io.chasehuegel.ecfabric.magic.TargetMode;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CustomStaffItem extends BowItem {
    private int staffPower;
    private int staffPunch;

    public CustomStaffItem(int power, int punch, Settings settings) {
        super(settings);
        this.staffPower = power;
        this.staffPunch = punch;
    }

    @Override
    public int getRange() {
        return 32;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (player.getAbilities().creativeMode || !TryGetSpellComponent(player).isEmpty()) {
            player.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }

        return TypedActionResult.fail(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        
        PlayerEntity player = (PlayerEntity)user;

        //  Try to find a spell component
        ItemStack componentStack = TryGetSpellComponent(player);
        if (componentStack.isEmpty()) {
            return;
        }

        //  Try to find an associated spell
        Spell spell = SpellManager.TryGetFromComponent(componentStack.getItem());
        if (spell == null) {
            return;
        }

        ItemStack castingStack = GetCastingStack(player);
        float strength = BowItem.getPullProgress(this.getMaxUseTime(castingStack) - remainingUseTicks);

        if (!player.hasStackEquipped(EquipmentSlot.HEAD))
            strength += 0.05f;
        if (!player.hasStackEquipped(EquipmentSlot.CHEST))
            strength += 0.25f;
        if (!player.hasStackEquipped(EquipmentSlot.LEGS))
            strength += 0.15f;
        if (!player.hasStackEquipped(EquipmentSlot.FEET))
            strength += 0.05f;

        HitResult hitResult = TryGetTarget(player);
        if (strength < 0.1f || ((hitResult == null || hitResult.getType() == Type.MISS) && spell.Type != SpellType.PROJECTILE)) {
            return;
        }

        if (!IsTargetValidForSpellType(spell.Type, hitResult)) {
            return;
        }

        int powerLevel = spell.GetLevel(EnchantmentHelper.getLevel(Enchantments.POWER, castingStack) + staffPower);
        int punchLevel = spell.GetLevel(EnchantmentHelper.getLevel(Enchantments.PUNCH, castingStack) + staffPunch);
        boolean flame = EnchantmentHelper.getLevel(Enchantments.FLAME, castingStack) > 0;
        boolean infinity = EnchantmentHelper.getLevel(Enchantments.INFINITY, castingStack) > 0;
        
        if (!world.isClient) {
            float power = 1f + (powerLevel * 0.5f);

            switch (spell.Type) {
                case PROJECTILE:
                    TriggerProjectile(world, player, spell, castingStack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case INSTANT:
                    TriggerInstant(world, player, (hitResult instanceof EntityHitResult) ? ((EntityHitResult)hitResult).getEntity() : null, spell, castingStack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case SELF:
                    TriggerSelf(world, player, spell, castingStack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case SUMMON:
                    TriggerSummon(world, player, hitResult, spell, castingStack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                default:
                    break;
            }
            
            castingStack.damage(1, player, p -> p.sendToolBreakStatus(GetCastingHand(player)));

            TryPlayCastEffect((ServerWorld)world, (ServerPlayerEntity)player, spell);
            TryPlayHitEffect((ServerWorld)world, (ServerPlayerEntity)player, spell, hitResult);
        }

        //  Don't consume components in creative mode
        if (!player.getAbilities().creativeMode) {
            componentStack.decrement(1);
            if (componentStack.isEmpty()) {
                player.getInventory().removeOne(componentStack);
            }
        }
        
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    private boolean TriggerProjectile(World world, PlayerEntity player, Spell spell, ItemStack stack, float strength, float power, int powerLevel, int punchLevel, boolean flame, boolean infinity) {
        Entity entity = spell.ProjectileEntity.create((ServerWorld) world, null, null, player, BlockPos.ORIGIN, SpawnReason.COMMAND, false, false);
        entity.setPosition(player.getEyePos());
        entity.setVelocity(getProjectileVelocity(player, player.getPitch(), player.getYaw(), 0.0f, strength * spell.ProjectileVelocity, 1.0f));
        
        //  Infinity removes gravity
        entity.setNoGravity(infinity);

        
        //  Punch increases velocity
        if (punchLevel > 0) {
            entity.setVelocity(entity.getVelocity().multiply(1 + punchLevel * 0.25f));
        }
        
        //  Infinity removes drag
        if (infinity) {
            SpellScheduler.SetEntityVelocity(entity, entity.getVelocity());
        }
        
        //  Flame ignites
        if (flame) {
            entity.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }

        //  Apply effects to living entities
        if (spell.EffectEnabled && entity instanceof LivingEntity) {
            ((LivingEntity)entity).addStatusEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, infinity), player);
        }

        //  Projectiles need to have their owner set and maintain a constant velocity
        if (entity instanceof ProjectileEntity) {
            ProjectileEntity projectileEntity = (ProjectileEntity)entity;
            projectileEntity.setOwner(player);

            //  Power increases fireball explosion
            if (entity instanceof FireballEntity) {
                FireballEntity fireballEntity = (FireballEntity)entity;
                try {
                    FieldUtils.writeField(fireballEntity, "explosionPower", (int)(power * strength), true);
                } catch (Exception ex) {}                
            }

            //  Flame charges wither skulls
            else if (entity instanceof WitherSkullEntity) {
                WitherSkullEntity witherSkullEntity = (WitherSkullEntity)entity;
                witherSkullEntity.setCharged(flame);
            }

            //  Apply effects to potions
            else if (spell.EffectEnabled && entity instanceof PotionEntity) {
                PotionEntity potionEntity = (PotionEntity)entity;
                ItemStack potionItemStack = Items.SPLASH_POTION.getDefaultStack();
                potionItemStack = PotionUtil.setCustomPotionEffects(potionItemStack, Arrays.asList(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, infinity)));
                potionEntity.setItem(potionItemStack);
            }
        }
        
        SpellScheduler.RemoveEntityAfterDistance(entity, getRange() * strength * 2f);
        SpellScheduler.RemoveEntityAfterLifetime(entity, (int)(20 * power * strength));
        world.spawnEntity(entity);
        return true;
    }

    private boolean TriggerSummon(World world, PlayerEntity player, HitResult hitResult, Spell spell, ItemStack stack, float strength, float power, int powerLevel, int punchLevel, boolean flame, boolean infinity) {
        if (hitResult.getType() == Type.MISS) {
            return false;
        }

        Vec3d targetPosition = hitResult.getType() == Type.BLOCK ? hitResult.getPos() : ((EntityHitResult)hitResult).getEntity().getPos();
        
        Entity entity = spell.SummonEntity.create((ServerWorld) world, null, null, player, BlockPos.ORIGIN, SpawnReason.COMMAND, false, false);
        entity.setPosition(targetPosition);
        
        //  Apply effects to living entities
        if (spell.EffectEnabled && entity instanceof LivingEntity) {
            ((LivingEntity)entity).addStatusEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, infinity), player);
        }

        //  Cloud entities can apply effects or be instant aoe damage
        else if (entity instanceof AreaEffectCloudEntity) {
            AreaEffectCloudEntity areaEffectCloudEntity= (AreaEffectCloudEntity)entity;
            areaEffectCloudEntity.setOwner(player);
            areaEffectCloudEntity.setRadius(3.0f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(spell.SummonDuration);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());

            if (spell.DamageEnabled) {
                List<Entity> hitEntities = world.getOtherEntities(entity, new Box(entity.getX() - 3.0, entity.getY() - 3.0, entity.getZ() - 3.0, entity.getX() + 3.0, entity.getY() + 6.0 + 3.0, entity.getZ() + 3.0), Entity::isAlive);
                for (Entity hitEntity : hitEntities) {
                    TriggerInstant(world, player, hitEntity, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                }
            } else if (spell.EffectEnabled) {
                areaEffectCloudEntity.addEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, infinity));
            }
        }

        //  If damage is enabled, lightning is actually an instant effect aoe
        else if (spell.DamageEnabled && entity instanceof LightningEntity) {
            LightningEntity lightningEntity = (LightningEntity)entity;
            lightningEntity.setCosmetic(true);
            
            try {
                FieldUtils.writeField(lightningEntity, "ignoreCameraFrustum", true, true);
                FieldUtils.writeField(lightningEntity, "ambientTick", 2, true);
                FieldUtils.writeField(lightningEntity, "seed", EternalCraft.Random.nextLong(), true);
                FieldUtils.writeField(lightningEntity, "remainingActions", EternalCraft.Random.nextInt(3) + 1, true);
            } catch (Exception ex) {}
            
            List<Entity> hitEntities = world.getOtherEntities(entity, new Box(entity.getX() - 3.0 - punchLevel, entity.getY() - 3.0 - punchLevel, entity.getZ() - 3.0 - punchLevel, entity.getX() + 3.0 + punchLevel, entity.getY() + 3.0 + punchLevel, entity.getZ() + 3.0 + punchLevel), Entity::isAlive);
            for (Entity hitEntity : hitEntities) {
                if (hitEntity instanceof LivingEntity)
                {
                    hitEntity.onStruckByLightning((ServerWorld) world, lightningEntity);
                    TriggerInstant(world, player, hitEntity, spell, stack, strength, power, powerLevel, punchLevel,
                            flame, infinity);
                }
            }
        }
        
        //  Flame ignites
        if (flame) {
            entity.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }
        
        //  Infinity allows summons to be permanent
        if (!infinity) {
            SpellScheduler.RemoveEntityAfterLifetime(entity, (int)(spell.SummonDuration * power * strength));
        }

        world.spawnEntity(entity);
        return true;
    }

    private boolean TriggerInstant(World world, PlayerEntity player, Entity other, Spell spell, ItemStack stack, float strength, float power, int powerLevel, int punchLevel, boolean flame, boolean infinity) {        
        if (other == null) {
            return false;
        }

        if (spell.DamageEnabled) {
            if (spell.DamageTarget == TargetMode.OTHER)
                other.damage(DamageSource.magic(player, player), spell.GetDamage(powerLevel) * strength);
            else
                player.damage(DamageSource.magic(player, player), spell.GetDamage(powerLevel) * strength);
            
            if (flame) {
                if (spell.DamageTarget == TargetMode.OTHER)
                    other.setFireTicks((int) (100 * strength));
                else
                    player.setFireTicks((int) (100 * strength));
            }
        }

        if (spell.HealEnabled) {
            if (spell.HealTarget == TargetMode.OTHER && other instanceof LivingEntity)
                ((LivingEntity)other).heal(spell.GetHeal(powerLevel) * strength);
            else
                player.heal(spell.GetHeal(powerLevel) * strength);
        }

        if (spell.EffectEnabled) {
            if (spell.EffectTarget == TargetMode.OTHER && other instanceof LivingEntity)
                ((LivingEntity)other).addStatusEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, infinity), player);
            else
                player.addStatusEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, false), player);
        }

        return true;
    }

    private boolean TriggerSelf(World world, PlayerEntity player, Spell spell, ItemStack stack, float strength, float power, int powerLevel, int punchLevel, boolean flame, boolean infinity) {        
        if (spell.DamageEnabled) {
            player.damage(DamageSource.magic(player, player), spell.GetDamage(powerLevel));
        }

        if (spell.HealEnabled) {
            player.heal(spell.GetHeal(powerLevel));
        }

        if (spell.EffectEnabled) {
            player.addStatusEffect(GetModifiedSpellEffect(spell, strength, powerLevel, punchLevel, false), player);
        }

        if (flame) {
            player.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }
        
        return true;
    }

    private boolean IsTargetValidForSpellType(SpellType type, HitResult hitResult) {
        switch (type) {
            case INSTANT:
                return hitResult.getType() == Type.ENTITY;
            case SUMMON:
                return hitResult.getType() != Type.MISS;
            default:
                return true;
        }
    }

    private ItemStack TryGetSpellComponent(PlayerEntity player) {
        ItemStack componentStack = player.getOffHandStack();
        Spell spell = SpellManager.TryGetFromComponent(componentStack.getItem());

        if (spell == null) {
            componentStack = player.getMainHandStack();
            spell = SpellManager.TryGetFromComponent(componentStack.getItem());
        }

        if (spell != null) {
            return componentStack;
        }

        for (int i = 0; i < player.getInventory().size(); ++i) {
            componentStack = player.getInventory().getStack(i);

            spell = SpellManager.TryGetFromComponent(componentStack.getItem());

            if (spell != null) {
                return componentStack;
            }
        }

        return ItemStack.EMPTY;
    }

    private ItemStack GetCastingStack(PlayerEntity player) {        
        if (player.getMainHandStack().getItem() instanceof CustomStaffItem)
            return player.getMainHandStack();

        return player.getOffHandStack();
    }
    
    private Hand GetCastingHand(PlayerEntity player) {        
        if (player.getMainHandStack().getItem() instanceof CustomStaffItem)
            return Hand.MAIN_HAND;

        return Hand.OFF_HAND;
    }

    private HitResult TryGetTarget(PlayerEntity player) {
        //  Try an entity raycast
        HitResult hitResult = RaycastEntity(player, getRange());

        //  ...on fail, try a block raycast
        if (hitResult == null || hitResult.getType() == Type.MISS) {
            hitResult = player.raycast(getRange(), 1f, false);
        }

        return hitResult;
    }

    public EntityHitResult RaycastEntity(Entity entity2, int maxDistance) {
        Vec3d vec3d = entity2.getEyePos();
        Vec3d vec3d2;
        int i;

        EntityHitResult entityHitResult = ProjectileUtil.raycast(entity2, vec3d, vec3d.add(vec3d2 = entity2.getRotationVec(1.0f).multiply(maxDistance)), entity2.getBoundingBox().stretch(vec3d2).expand(1.0), entity -> !entity.isSpectator() && entity.collides(), i = maxDistance * maxDistance);

        if (entityHitResult != null && vec3d.squaredDistanceTo(entityHitResult.getPos()) > (double)i) {
            return null;
        }

        return entityHitResult;
    }

    private StatusEffectInstance GetModifiedSpellEffect(Spell spell, float strength, int powerLevel, int punchLevel, boolean infinity) {
        return new StatusEffectInstance(
            spell.Effect,
            infinity ? Integer.MAX_VALUE : (int)((spell.GetEffectDuration(powerLevel)) * strength),
            (int)((spell.GetEffectAmplifier(punchLevel)) * strength),
            spell.ShowEffectParticles,
            spell.IsEffectVisible
        );
    }

    private void CreateSpellParticleEffect(ServerWorld world, Vec3d pos, float size, ParticleEffect particle, int particleCount) {
        double x = pos.getX();
        double y = pos.getY() + 0.5f;
        double z = pos.getZ();
        
        world.spawnParticles(particle, x, y, z, particleCount, (Math.random()-0.5f) * size, (Math.random()-0.5f) * size, (Math.random()-0.5f) * size, 0);
    }

    public void TryPlayCastEffect(ServerWorld world, ServerPlayerEntity player, Spell spell) {
        CreateSpellParticleEffect(world, player.getPos(), (float)player.getBoundingBox().getAverageSideLength()*2f, spell.CastParticle, spell.CastParticleCount);
        world.playSound(null, player.getBlockPos(), spell.CastSound, SoundCategory.PLAYERS, 1.0f, 1.0f / (EternalCraft.Random.nextFloat() * 0.4f + 1.2f));
        int powerLevel = spell.GetLevel(EnchantmentHelper.getLevel(Enchantments.POWER, player.getActiveItem()) + staffPower);
        player.sendMessage(Text.of(spell.Name + " " + ToRomanNumeral(spell.GetLevel(powerLevel))), true);
    }

    public void TryPlayHitEffect(ServerWorld world, ServerPlayerEntity player, Spell spell, HitResult hitResult) {
        switch (spell.Type) {
            case INSTANT:
                Entity e = ((EntityHitResult)hitResult).getEntity();
                world.playSound(null, e.getBlockPos(), spell.HitSound, SoundCategory.PLAYERS, 1.0f, 1.0f / (EternalCraft.Random.nextFloat() * 0.4f + 1.2f));
                
                CreateSpellParticleEffect(world, e.getEyePos(), (float)e.getBoundingBox().getAverageSideLength()*2f, spell.HitParticle, spell.HitParticleCount);

                float distance = (float) e.getEyePos().distanceTo(player.getEyePos());
                Vec3d direction = e.getEyePos().subtract(player.getEyePos()).normalize();
                Vec3d origin = player.getEyePos().subtract(new Vec3d(0f, 0.5f, 0f));
                for (float increment = 0f; increment < distance; increment += 0.5f) {
                    CreateSpellParticleEffect(world, origin.add(direction.multiply(increment)), 0.5f, spell.HitParticle, (int)Math.min(1, spell.HitParticleCount*0.25f));
                }
                break;
            case SUMMON:
                Vec3d p = hitResult.getType() == Type.ENTITY ? ((EntityHitResult)hitResult).getEntity().getPos() : hitResult.getPos();
                float s = hitResult.getType() == Type.ENTITY ? (float)((EntityHitResult)hitResult).getEntity().getBoundingBox().getAverageSideLength()*2f : 1.5f;

                world.playSound(null, new BlockPos(p), spell.HitSound, SoundCategory.PLAYERS, 1.0f, 1.0f / (EternalCraft.Random.nextFloat() * 0.4f + 1.2f));
                CreateSpellParticleEffect(world, p, s, spell.HitParticle, spell.HitParticleCount);
                break;
            default:
                break;
        }
    }

    private Vec3d getProjectileVelocity(Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {        
        float x = -MathHelper.sin(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        float y = -MathHelper.sin((pitch + roll) * ((float)Math.PI / 180));
        float z = MathHelper.cos(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));

        Vec3d shooterVelocity = shooter.getVelocity();

        Vec3d projectileVelocity = calculateProjectileVelocity(x, y, z, speed, divergence);
        projectileVelocity = projectileVelocity.add(shooterVelocity.x, shooter.isOnGround() ? 0.0 : shooterVelocity.y, shooterVelocity.z);

        return projectileVelocity;
    }

    private Vec3d calculateProjectileVelocity(double x, double y, double z, float speed, float divergence) {
        Vec3d vec3d = new Vec3d(x, y, z).normalize().add(EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence, EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence, EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence).multiply(speed);
        return vec3d;
    }

    private String ToRomanNumeral(int number) {
        return String.join("", "I".repeat(number))
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }
}