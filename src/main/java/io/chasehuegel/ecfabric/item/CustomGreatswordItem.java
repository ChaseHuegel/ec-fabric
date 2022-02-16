package io.chasehuegel.ecfabric.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class CustomGreatswordItem extends CustomSwordItem {
    private final float attackDamage;

    public CustomGreatswordItem(ToolMaterial material, int attackDamage, float attackSpeed, float attackRange, Settings settings) {
        super(material, attackDamage, attackSpeed, attackRange, settings);

        this.attackDamage = (float)attackDamage + material.getAttackDamage();
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (!attacker.getOffHandStack().isEmpty()) {
            target.heal(attackDamage / 2);
        }

        return true;
    }
}