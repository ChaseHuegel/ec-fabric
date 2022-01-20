package io.chasehuegel.ecfabric.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;

import net.minecraft.item.Item;

public class CustomShieldItem extends FabricShieldItem {
    public CustomShieldItem(Settings settings, int cooldownTicks, int enchantability, Item... repairItems) {
        super(settings, cooldownTicks, enchantability, repairItems);
    }
}