package io.chasehuegel.ecfabric.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CustomMaceItem extends SwordItem {
    public CustomMaceItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}