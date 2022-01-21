package io.chasehuegel.ecfabric.item;

import java.util.Optional;
import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.item.Trinkets.AmethystAmulet;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomItems {
    public static final Item EMPTY_CAN = CustomItems.register("empty_can", new WaterCan(Fluids.EMPTY, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    public static final Item WATER_CAN = CustomItems.register("water_can", new WaterCan(Fluids.WATER, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    public static final Item LAVA_CAN = CustomItems.register("lava_can", new WaterCan(Fluids.LAVA, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    
    public static final Item AMETHYST_INGOT = CustomItems.register("amethyst_ingot", new Item(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_SWORD = CustomItems.register("amethyst_sword", new CustomSwordItem(CustomToolMaterials.AMETHYST, 3, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_SHOVEL = CustomItems.register("amethyst_shovel", new CustomShovelItem(CustomToolMaterials.AMETHYST, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_PICKAXE = CustomItems.register("amethyst_pickaxe", new CustomPickaxeItem(CustomToolMaterials.AMETHYST, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_AXE = CustomItems.register("amethyst_axe", new CustomAxeItem(CustomToolMaterials.AMETHYST, 5, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_HOE = CustomItems.register("amethyst_hoe", new CustomHoeItem(CustomToolMaterials.AMETHYST, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_DAGGER = CustomItems.register("amethyst_dagger", new CustomDaggerItem(CustomToolMaterials.AMETHYST, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_GREATSWORD = CustomItems.register("amethyst_greatsword", new CustomGreatswordItem(CustomToolMaterials.AMETHYST, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_MACE = CustomItems.register("amethyst_mace", new CustomMaceItem(CustomToolMaterials.AMETHYST, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_SPEAR = CustomItems.register("amethyst_spear", new CustomSpearItem(CustomToolMaterials.AMETHYST, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_STAFF = CustomItems.register("amethyst_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_SHIELD = CustomItems.register("amethyst_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(436).group(CustomItemGroup.ECFABRIC), 140, 22, Items.AMETHYST_SHARD));
    public static final Item AMETHYST_AMULET = CustomItems.register("amethyst_amulet", new AmethystAmulet(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    
    public static final Item GOLDEN_SHIELD = CustomItems.register("golden_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 60, 50, Items.GOLD_INGOT));
    
    public static void Initialize() {}

    private static <T> Optional<T> createEmptyOptional(T of) {
        return Optional.empty();
    }

    private static Item register(Block block) {
        return CustomItems.register(new BlockItem(block, new FabricItemSettings()));
    }

    private static Item register(Block block, ItemGroup group) {
        return CustomItems.register(new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Item register(Block block, Optional<ItemGroup> group2) {
        return group2.map(group -> CustomItems.register(block, group)).orElseGet(() -> CustomItems.register(block));
    }

    private static Item register(Block block, ItemGroup group, Block ... blocks) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings().group(group));
        for (Block block2 : blocks) {
            Item.BLOCK_ITEMS.put(block2, blockItem);
        }
        return CustomItems.register(blockItem);
    }

    private static Item register(BlockItem item) {
        return CustomItems.register(item.getBlock(), (Item)item);
    }

    protected static Item register(Block block, Item item) {
        return CustomItems.register(Registry.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return CustomItems.register(new Identifier(EternalCraft.Namespace, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, id, item);
    }
}
