package io.chasehuegel.ecfabric.item;

import java.util.Optional;

import com.google.common.collect.ImmutableMultimap;

import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ArmorItem;
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
    public static final Item GOLDEN_SHIELD = CustomItems.register("golden_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 60, 50, 0f, 0f, 0.5f, Items.GOLD_INGOT));
    
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
    public static final Item AMETHYST_SHIELD = CustomItems.register("amethyst_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 100, 22, 0f, 2f, 0f, Items.AMETHYST_SHARD));
    public static final Item AMETHYST_HELMET = CustomItems.register("amethyst_helmet", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_CHESTPLATE = CustomItems.register("amethyst_chestplate", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_LEGGINGS = CustomItems.register("amethyst_leggings", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_BOOTS = CustomItems.register("amethyst_boots", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_AMULET = CustomItems.register("amethyst_amulet", new CustomTrinket(new FabricItemSettings().maxCount(1).group(CustomItemGroup.ECFABRIC),
                                                ImmutableMultimap.of(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(String.format("%s:movement_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                                                ));
    
    public static final Item STEEL_INGOT = CustomItems.register("steel_ingot", new Item(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_SWORD = CustomItems.register("steel_sword", new CustomSwordItem(CustomToolMaterials.STEEL, 3, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_SHOVEL = CustomItems.register("steel_shovel", new CustomShovelItem(CustomToolMaterials.STEEL, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_PICKAXE = CustomItems.register("steel_pickaxe", new CustomPickaxeItem(CustomToolMaterials.STEEL, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_AXE = CustomItems.register("steel_axe", new CustomAxeItem(CustomToolMaterials.STEEL, 5, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_HOE = CustomItems.register("steel_hoe", new CustomHoeItem(CustomToolMaterials.STEEL, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_DAGGER = CustomItems.register("steel_dagger", new CustomDaggerItem(CustomToolMaterials.STEEL, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_GREATSWORD = CustomItems.register("steel_greatsword", new CustomGreatswordItem(CustomToolMaterials.STEEL, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_MACE = CustomItems.register("steel_mace", new CustomMaceItem(CustomToolMaterials.STEEL, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_SPEAR = CustomItems.register("steel_spear", new CustomSpearItem(CustomToolMaterials.STEEL, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_STAFF = CustomItems.register("steel_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_SHIELD = CustomItems.register("steel_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(400).group(CustomItemGroup.ECFABRIC), 200, 15, 2f, 0f, 0f, CustomItems.STEEL_INGOT));
    public static final Item STEEL_HELMET = CustomItems.register("steel_helmet", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_CHESTPLATE = CustomItems.register("steel_chestplate", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_LEGGINGS = CustomItems.register("steel_leggings", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_BOOTS = CustomItems.register("steel_boots", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_LINK = CustomItems.register("steel_link", new CustomTrinket(new FabricItemSettings().maxCount(1).group(CustomItemGroup.ECFABRIC),
                                            ImmutableMultimap.of(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(String.format("%s:toughness", EternalCraft.Namespace), 0.25f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                                            ));

    public static final Item BONE_SWORD = CustomItems.register("bone_sword", new CustomSwordItem(CustomToolMaterials.BONE, 3, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_SHOVEL = CustomItems.register("bone_shovel", new CustomShovelItem(CustomToolMaterials.BONE, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_PICKAXE = CustomItems.register("bone_pickaxe", new CustomPickaxeItem(CustomToolMaterials.BONE, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_AXE = CustomItems.register("bone_axe", new CustomAxeItem(CustomToolMaterials.BONE, 5, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_HOE = CustomItems.register("bone_hoe", new CustomHoeItem(CustomToolMaterials.BONE, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_DAGGER = CustomItems.register("bone_dagger", new CustomDaggerItem(CustomToolMaterials.BONE, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_GREATSWORD = CustomItems.register("bone_greatsword", new CustomGreatswordItem(CustomToolMaterials.BONE, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_MACE = CustomItems.register("bone_mace", new CustomMaceItem(CustomToolMaterials.BONE, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_SPEAR = CustomItems.register("bone_spear", new CustomSpearItem(CustomToolMaterials.BONE, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_STAFF = CustomItems.register("bone_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_SHIELD = CustomItems.register("bone_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(250).group(CustomItemGroup.ECFABRIC), 200, 30, 0f, 0f, 0.1f, Items.BONE));
    public static final Item BONE_HELMET = CustomItems.register("bone_helmet", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_CHESTPLATE = CustomItems.register("bone_chestplate", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_LEGGINGS = CustomItems.register("bone_leggings", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_BOOTS = CustomItems.register("bone_boots", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_IDOL = CustomItems.register("bone_idol", new CustomTrinket(new FabricItemSettings().maxCount(1).group(CustomItemGroup.ECFABRIC),
                                            ImmutableMultimap.of(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(String.format("%s:health", EternalCraft.Namespace), 4f, EntityAttributeModifier.Operation.ADDITION))
                                            ));

    
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
