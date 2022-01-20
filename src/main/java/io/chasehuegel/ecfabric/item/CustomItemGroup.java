package io.chasehuegel.ecfabric.item;

import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class CustomItemGroup {
    public static final ItemGroup ECFABRIC = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "eternalcraft"),
        () -> CustomItems.AMETHYST_SWORD.getDefaultStack());
    
    public static void Initialize() {}
}
