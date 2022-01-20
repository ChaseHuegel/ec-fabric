package io.chasehuegel.ecfabric.item;

import java.util.function.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class CustomStaffItem extends BowItem {
    public static final Item AMMO_ITEM = Items.GLOWSTONE_DUST;
    public static final Predicate<ItemStack> STAFF_PROJECTILES = stack -> stack.isOf(CustomStaffItem.AMMO_ITEM);
    
    public CustomStaffItem(Settings settings) {
        super(settings);
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
        boolean bl2;
        int i;
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
        if ((double)(f = BowItem.getPullProgress(i = this.getMaxUseTime(stack) - remainingUseTicks)) < 0.1) {
            return;
        }

        boolean bl3 = bl2 = bl && itemStack.isOf(CustomStaffItem.AMMO_ITEM);
        
        if (!world.isClient) {
            int k;
            int j;
            int explosionPower = 1;
            
            if ((j = EnchantmentHelper.getLevel(Enchantments.POWER, stack)) > 0) {
                explosionPower += (int)(j * 0.5f + 0.5f);
            }
            if ((k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack)) > 0) {
                explosionPower += 1;
            }

            FireballEntity persistentProjectileEntity = new FireballEntity(world, playerEntity, 0f, 0f, 0f, explosionPower);
            persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, f * 3.0f, 1.0f);
            
            if (f == 1.0f) {
                persistentProjectileEntity.setOnFire(true);
            }
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                persistentProjectileEntity.setOnFire(true);
            }

            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
            world.spawnEntity(persistentProjectileEntity);
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
}