package io.chasehuegel.ecfabric.item;

import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class CustomItemGroup {
    public static final ItemGroup TOOLS = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "tools"),
        () -> Items.WATER_CAN.getDefaultStack());

    public static final ItemGroup COMBAT = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "combat"),
        () -> Items.WATER_CAN.getDefaultStack());
    
    public static void Initialize() {}
}
