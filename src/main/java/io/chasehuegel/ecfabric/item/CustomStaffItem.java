package io.chasehuegel.ecfabric.item;

import java.util.function.Predicate;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.magic.Spell;
import io.chasehuegel.ecfabric.magic.SpellManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CustomStaffItem extends BowItem {
    public static final Item AMMO_ITEM = Items.GLOWSTONE_DUST;
    public static final Predicate<ItemStack> STAFF_PROJECTILES = stack -> stack.isOf(CustomStaffItem.AMMO_ITEM);
    
    public CustomStaffItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return STAFF_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 32;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        Spell spell = null;

        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)user;

            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack inventoryStack = player.getInventory().getStack(i);

                spell = SpellManager.GetFromComponent(inventoryStack.getItem());

                if (spell != null) {
                    break;
                }
            }
        }

        if (spell == null) {
            return;
        }

        boolean bl2;
        float f;
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)user;
        boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack itemStack = playerEntity.getArrowType(stack);
        if (itemStack.isEmpty() && !bl) {
            return;
        }
        if (itemStack.isEmpty()) {
            itemStack = new ItemStack(CustomStaffItem.AMMO_ITEM);
        }
        if ((double)(f = BowItem.getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks)) < 0.1) {
            return;
        }

        bl2 = bl && itemStack.isOf(CustomStaffItem.AMMO_ITEM);
        
        if (!world.isClient) {
            int j;
            int explosionPower = 1;
            
            if ((j = EnchantmentHelper.getLevel(Enchantments.POWER, stack)) > 0) {
                explosionPower += (int)(j * 0.5f + 0.5f);
            }
            if (EnchantmentHelper.getLevel(Enchantments.PUNCH, stack) > 0) {
                explosionPower += 1;
            }

            Entity projectile = spell.ProjectileEntity.create((ServerWorld) world, null, null, playerEntity, BlockPos.ORIGIN, SpawnReason.COMMAND, false, false);
            projectile.setNoGravity(false);
            projectile.setPosition(playerEntity.getEyePos());
            projectile.setVelocity(getProjectileVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, f * spell.ProjectileVelocity, 1.0f));

            if (projectile instanceof ProjectileEntity) {
                ((ProjectileEntity)projectile).setOwner(playerEntity);
            }
            
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                projectile.setOnFire(true);
            }

            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
            world.spawnEntity(projectile);
        }

        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
        
        if (!bl2 && !playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
            if (itemStack.isEmpty()) {
                playerEntity.getInventory().removeOne(itemStack);
            }
        }

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    public Vec3d getProjectileVelocity(Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        Vec3d vec3d;
        
        float f = -MathHelper.sin(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        float g = -MathHelper.sin((pitch + roll) * ((float)Math.PI / 180));
        float h = MathHelper.cos(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        vec3d = getProjectileVelocity(f, g, h, speed, divergence);
        Vec3d shooterVelocity = shooter.getVelocity();
        vec3d = vec3d.add(shooterVelocity.x, shooter.isOnGround() ? 0.0 : shooterVelocity.y, shooterVelocity.z);

        return vec3d;
    }

    public Vec3d getProjectileVelocity(double x, double y, double z, float speed, float divergence) {
        Vec3d vec3d = new Vec3d(x, y, z).normalize().add(EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence, EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence, EternalCraft.Random.nextGaussian() * (double)0.0075f * (double)divergence).multiply(speed);
        return vec3d;
    }
}