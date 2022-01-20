package io.chasehuegel.ecfabric.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CustomSpearItem extends SwordItem {
    public CustomSpearItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}