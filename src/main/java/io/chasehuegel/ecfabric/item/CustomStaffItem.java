package io.chasehuegel.ecfabric.item;

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
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CustomStaffItem extends BowItem {    
    public CustomStaffItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        
        Spell spell = null;
        ItemStack componentStack = null;
        PlayerEntity player = (PlayerEntity)user;

        //  Try to find a spell component
        for (int i = 0; i < player.getInventory().size(); ++i) {
            ItemStack inventoryStack = player.getInventory().getStack(i);

            spell = SpellManager.GetFromComponent(inventoryStack.getItem());

            if (spell != null) {
                componentStack = inventoryStack;
                break;
            }
        }

        float strength = BowItem.getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks);        
        if (spell == null && strength < 0.1f) {
            return;
        }

        
        if (!world.isClient) {
            switch (spell.Type) {
                case PROJECTILE:
                TriggerProjectile(world, player, spell, stack, strength);
                break;
                case INSTANT:
                break;
                case SUMMON:
                break;
            }
            
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
        
        //  Don't consume components in creative mode or with infinity
        if (!player.getAbilities().creativeMode && EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) <= 0) {
            componentStack.decrement(1);
            if (componentStack.isEmpty()) {
                player.getInventory().removeOne(componentStack);
            }
        }
        
        world.playSound(null, player.getX(), player.getY(), player.getZ(), spell.CastSound, SoundCategory.PLAYERS, 1.0f, 1.0f / (EternalCraft.Random.nextFloat() * 0.4f + 1.2f) + strength * 0.5f);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    private void TriggerProjectile(World world, PlayerEntity player, Spell spell, ItemStack stack, float strength) {
        int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
        if (powerLevel > 0) {
            // power += (int)(powerLevel * 0.5f + 0.5f);
        }

        if (EnchantmentHelper.getLevel(Enchantments.PUNCH, stack) > 0) {
        }

        Entity projectile = spell.ProjectileEntity.create((ServerWorld) world, null, null, player, BlockPos.ORIGIN, SpawnReason.COMMAND, false, false);
        projectile.setNoGravity(false);
        projectile.setPosition(player.getEyePos());
        projectile.setVelocity(getProjectileVelocity(player, player.getPitch(), player.getYaw(), 0.0f, strength * spell.ProjectileVelocity, 1.0f));

        if (projectile instanceof ProjectileEntity) {
            ((ProjectileEntity)projectile).setOwner(player);
        }
        
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
            projectile.setOnFire(true);
        }

        world.spawnEntity(projectile);
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