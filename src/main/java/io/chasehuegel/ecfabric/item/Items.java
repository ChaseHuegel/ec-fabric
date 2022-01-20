package io.chasehuegel.ecfabric.item;

import java.util.Optional;
import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final Item EMPTY_CAN = Items.register("empty_can", (Item)new WaterCan(Fluids.EMPTY, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    public static final Item WATER_CAN = Items.register("water_can", (Item)new WaterCan(Fluids.WATER, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    public static final Item LAVA_CAN = Items.register("lava_can", (Item)new WaterCan(Fluids.LAVA, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    
    public static final Item AMETHYST_SWORD = Items.register("amethyst_sword", (Item)new CustomSwordItem(CustomToolMaterials.AMETHYST, 3, -2.4f, new Item.Settings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_SHOVEL = Items.register("amethyst_shovel", (Item)new CustomShovelItem(CustomToolMaterials.AMETHYST, 1, -3.0f, new Item.Settings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_PICKAXE = Items.register("amethyst_pickaxe", (Item)new CustomPickaxeItem(CustomToolMaterials.AMETHYST, 1, -2.8f, new Item.Settings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_AXE = Items.register("amethyst_axe", (Item)new CustomAxeItem(CustomToolMaterials.AMETHYST, 5, -3.0f, new Item.Settings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_HOE = Items.register("amethyst_hoe", (Item)new CustomHoeItem(CustomToolMaterials.AMETHYST, -2, 0.0f, new Item.Settings().group(CustomItemGroup.TOOLS)));
    
    public static void Initialize() {}

    private static <T> Optional<T> createEmptyOptional(T of) {
        return Optional.empty();
    }

    private static Item register(Block block) {
        return Items.register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(Block block, ItemGroup group) {
        return Items.register(new BlockItem(block, new Item.Settings().group(group)));
    }

    private static Item register(Block block, Optional<ItemGroup> group2) {
        return group2.map(group -> Items.register(block, group)).orElseGet(() -> Items.register(block));
    }

    private static Item register(Block block, ItemGroup group, Block ... blocks) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings().group(group));
        for (Block block2 : blocks) {
            Item.BLOCK_ITEMS.put(block2, blockItem);
        }
        return Items.register(blockItem);
    }

    private static Item register(BlockItem item) {
        return Items.register(item.getBlock(), (Item)item);
    }

    protected static Item register(Block block, Item item) {
        return Items.register(Registry.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return Items.register(new Identifier(EternalCraft.Namespace, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, id, item);
    }
}
