package io.chasehuegel.ecfabric.item;

import net.minecraft.item.ToolMaterial;

public class CustomDaggerItem extends CustomSwordItem {
    public CustomDaggerItem(ToolMaterial material, int attackDamage, float attackSpeed, float attackRange, Settings settings) {
        super(material, attackDamage, attackSpeed, attackRange, settings);
    }
}