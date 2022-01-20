package io.chasehuegel.ecfabric.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CustomGreatswordItem extends SwordItem {
    public CustomGreatswordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}