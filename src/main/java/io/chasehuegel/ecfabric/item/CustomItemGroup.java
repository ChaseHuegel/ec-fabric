package io.chasehuegel.ecfabric.item;

import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class CustomItemGroup {
    public static final ItemGroup ECFABRIC = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "eternalcraft"),
        () -> CustomItems.OBSIDIAN_PEARL.getDefaultStack());

    public static final ItemGroup BLOCKS = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "blocks"),
        () -> CustomItems.STEEL_BLOCK.getDefaultStack());

    public static final ItemGroup TOOLS = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "tools"),
        () -> CustomItems.PLATINUM_PICKAXE.getDefaultStack());

    public static final ItemGroup COMBAT = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "combat"),
        () -> CustomItems.AMETHYST_SWORD.getDefaultStack());


    public static final ItemGroup TRINKETS = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "trinkets"),
        () -> CustomItems.AMETHYST_AMULET.getDefaultStack());

    public static final ItemGroup MISC = FabricItemGroupBuilder.build(
        new Identifier(EternalCraft.Namespace, "misc"),
        () -> CustomItems.STEEL_INGOT.getDefaultStack());
    
    public static void Initialize() {}
}
