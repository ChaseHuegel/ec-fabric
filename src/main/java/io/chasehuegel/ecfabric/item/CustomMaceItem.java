package io.chasehuegel.ecfabric.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;

public class CustomMaceItem extends CustomAxeItem {
    public CustomMaceItem(ToolMaterial material, int attackDamage, float attackSpeed, float attackRange, Settings settings) {
        super(material, attackDamage, attackSpeed, attackRange, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Vec3d velocity = attacker.getPos().subtract(target.getPos());
        target.takeKnockback(0.5f, velocity.x, velocity.z);

        return super.postHit(stack, target, attacker);
    }
}