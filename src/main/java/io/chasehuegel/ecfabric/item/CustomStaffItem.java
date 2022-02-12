package io.chasehuegel.ecfabric.item;

import java.util.Arrays;
import java.util.List;

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
import net.minecraft.potion.PotionUtil;
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
    public CustomStaffItem(Settings settings) {
        super(settings.maxCount(1));
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

        float strength = BowItem.getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks);    
        HitResult hitResult = TryGetTarget(player);
        if (strength < 0.1f || (hitResult.getType() == null && spell.Type != SpellType.PROJECTILE)) {
            return;
        }

        int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
        int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
        boolean flame = EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0;
        boolean infinity = EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        
        if (!world.isClient) {
            float power = 1f + (powerLevel * 0.5f);

            boolean success = false;
            switch (spell.Type) {
                case PROJECTILE:
                    success = TriggerProjectile(world, player, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case INSTANT:
                    success = TriggerInstant(world, player, (hitResult instanceof EntityHitResult) ? ((EntityHitResult)hitResult).getEntity() : null, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case SELF:
                    success = TriggerSelf(world, player, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                case SUMMON:
                    success = TriggerSummon(world, player, hitResult, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                    break;
                default:
                    break;
            }

            if (!success) {
                return;
            }
            
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
        
        //  Don't consume components in creative mode
        if (!player.getAbilities().creativeMode) {
            componentStack.decrement(1);
            if (componentStack.isEmpty()) {
                player.getInventory().removeOne(componentStack);
            }
        }
        
        player.sendMessage(Text.of(spell.Name), true);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), spell.CastSound, SoundCategory.PLAYERS, 1.0f, 1.0f / (EternalCraft.Random.nextFloat() * 0.4f + 1.2f) + strength * 0.5f);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    private boolean TriggerProjectile(World world, PlayerEntity player, Spell spell, ItemStack stack, float strength, float power, int powerLevel, int punchLevel, boolean flame, boolean infinity) {
        Entity entity = spell.ProjectileEntity.create((ServerWorld) world, null, null, player, BlockPos.ORIGIN, SpawnReason.COMMAND, false, false);
        entity.setPosition(player.getEyePos());
        
        //  Infinity removes gravity
        entity.setNoGravity(infinity);

        //  Infinity removes drag
        if (infinity) {
            entity.setVelocity(getProjectileVelocity(player, player.getPitch(), player.getYaw(), 0.0f, strength * spell.ProjectileVelocity, 1.0f));
        }

        //  Punch increases velocity
        if (punchLevel > 0) {
            entity.setVelocity(entity.getVelocity().multiply(1 + punchLevel * 0.5f));
        }

        //  Flame ignites
        if (flame) {
            entity.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }

        //  Apply effects to living entities
        if (spell.EffectEnabled && entity instanceof LivingEntity) {
            ((LivingEntity)entity).addStatusEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, infinity), player);
        }

        //  Projectiles need to have their owner set and maintain a constant velocity
        if (entity instanceof ProjectileEntity) {
            ProjectileEntity projectileEntity = (ProjectileEntity)entity;
            projectileEntity.setOwner(player);
            SpellScheduler.SetEntityVelocity(projectileEntity, projectileEntity.getVelocity());

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
                potionItemStack = PotionUtil.setCustomPotionEffects(potionItemStack, Arrays.asList(GetModifiedSpellEffect(spell, strength, power, punchLevel, infinity)));
                potionEntity.setItem(potionItemStack);
            }
        }
        
        SpellScheduler.RemoveEntityAfterDistance(entity, getRange() * strength);
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
            ((LivingEntity)entity).addStatusEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, infinity), player);
        }

        //  Cloud entities can apply effects or be instant aoe damage
        else if (entity instanceof AreaEffectCloudEntity) {
            AreaEffectCloudEntity areaEffectCloudEntity= (AreaEffectCloudEntity)entity;
            areaEffectCloudEntity.setOwner(player);
            areaEffectCloudEntity.setRadius(3.0f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());

            if (spell.DamageEnabled) {
                List<Entity> hitEntities = world.getOtherEntities(entity, new Box(entity.getX() - 3.0, entity.getY() - 3.0, entity.getZ() - 3.0, entity.getX() + 3.0, entity.getY() + 6.0 + 3.0, entity.getZ() + 3.0), Entity::isAlive);
                for (Entity hitEntity : hitEntities) {
                    TriggerInstant(world, player, hitEntity, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
                }
            } else if (spell.EffectEnabled) {
                areaEffectCloudEntity.addEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, infinity));
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
            
            List<Entity> hitEntities = world.getOtherEntities(entity, new Box(entity.getX() - 3.0, entity.getY() - 3.0, entity.getZ() - 3.0, entity.getX() + 3.0, entity.getY() + 6.0 + 3.0, entity.getZ() + 3.0), Entity::isAlive);
            for (Entity hitEntity : hitEntities) {
                TriggerInstant(world, player, hitEntity, spell, stack, strength, power, powerLevel, punchLevel, flame, infinity);
            }
        }
        
        //  Flame ignites
        if (flame) {
            entity.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }
        
        //  Infinity allows summons to be permanent
        if (!infinity) {
            SpellScheduler.RemoveEntityAfterLifetime(entity, (int)(300 * power * strength));
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
                other.damage(DamageSource.magic(player, player), spell.GetDamage(powerLevel));
            else
                player.damage(DamageSource.magic(player, player), spell.GetDamage(powerLevel));
            
            if (flame) {
                if (spell.DamageTarget == TargetMode.OTHER)
                    other.setFireTicks(100);
                else
                    player.setFireTicks(100);
            }
        }

        if (spell.HealEnabled) {
            if (spell.HealTarget == TargetMode.OTHER && other instanceof LivingEntity)
                ((LivingEntity)other).heal(spell.GetHeal(powerLevel));
            else
                player.heal(spell.GetHeal(powerLevel));
        }

        if (spell.EffectEnabled) {
            if (spell.EffectTarget == TargetMode.OTHER && other instanceof LivingEntity)
                ((LivingEntity)other).addStatusEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, infinity), player);
            else
                player.addStatusEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, false), player);
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
            player.addStatusEffect(GetModifiedSpellEffect(spell, strength, power, punchLevel, false), player);
        }

        if (flame) {
            player.setFireTicks(infinity ? Integer.MAX_VALUE : 100);
        }
        
        return true;
    }

    private ItemStack TryGetSpellComponent(PlayerEntity player) {
        Spell spell = SpellManager.TryGetFromComponent(player.getOffHandStack().getItem());
        if (spell != null) {
            return player.getOffHandStack();
        }
        else for (int i = 0; i < player.getInventory().size(); ++i) {
            ItemStack inventoryStack = player.getInventory().getStack(i);

            spell = SpellManager.TryGetFromComponent(inventoryStack.getItem());

            if (spell != null) {
                return inventoryStack;
            }
        }

        return ItemStack.EMPTY;
    }

    private HitResult TryGetTarget(PlayerEntity player) {
        //  Try an entity raycast
        HitResult hitResult = RaycastEntity(player, getRange());

        //  ...on fail, try a block raycast
        if (hitResult.getType() == Type.MISS) {
            hitResult = player.raycast(getRange(), 1f, false);
        }

        return hitResult;
    }

    public static EntityHitResult RaycastEntity(Entity entity, int maxDistance) {
        Vec3d origin = entity.getEyePos();
        Vec3d projection = origin.add(projection = entity.getRotationVec(1.0f).multiply(maxDistance));
        EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, origin, projection, entity.getBoundingBox().stretch(projection).expand(1.0), e -> !e.isSpectator() && e.collides(), maxDistance * maxDistance);
        
        return entityHitResult;
    }

    private StatusEffectInstance GetModifiedSpellEffect(Spell spell, float strength, float power, int punchLevel, boolean infinity) {
        return new StatusEffectInstance(
            spell.Effect,
            infinity ? Integer.MAX_VALUE : (int)((spell.EffectDuration * power) * strength),
            (int)((spell.EffectAmplifier + punchLevel) * strength),
            spell.ShowEffectParticles,
            spell.IsEffectVisible
        );
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
}