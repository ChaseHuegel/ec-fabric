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
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomItems {
    public static final Item EMPTY_CAN = CustomItems.register("empty_can", new WaterCan(Fluids.EMPTY, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    public static final Item WATER_CAN = CustomItems.register("water_can", new WaterCan(Fluids.WATER, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    public static final Item LAVA_CAN = CustomItems.register("lava_can", new WaterCan(Fluids.LAVA, new FabricItemSettings().group(CustomItemGroup.ECFABRIC).maxDamage(16)));
    
    public static final Item WOODEN_DAGGER = CustomItems.register("wooden_dagger", new CustomDaggerItem(ToolMaterials.WOOD, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item WOODEN_GREATSWORD = CustomItems.register("wooden_greatsword", new CustomGreatswordItem(ToolMaterials.WOOD, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item WOODEN_MACE = CustomItems.register("wooden_mace", new CustomMaceItem(ToolMaterials.WOOD, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item WOODEN_SPEAR = CustomItems.register("wooden_spear", new CustomSpearItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STONE_DAGGER = CustomItems.register("stone_dagger", new CustomDaggerItem(ToolMaterials.STONE, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STONE_GREATSWORD = CustomItems.register("stone_greatsword", new CustomGreatswordItem(ToolMaterials.STONE, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STONE_MACE = CustomItems.register("stone_mace", new CustomMaceItem(ToolMaterials.STONE, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STONE_SPEAR = CustomItems.register("stone_spear", new CustomSpearItem(ToolMaterials.STONE, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item IRON_DAGGER = CustomItems.register("iron_dagger", new CustomDaggerItem(ToolMaterials.IRON, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item IRON_GREATSWORD = CustomItems.register("iron_greatsword", new CustomGreatswordItem(ToolMaterials.IRON, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item IRON_MACE = CustomItems.register("iron_mace", new CustomMaceItem(ToolMaterials.IRON, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item IRON_SPEAR = CustomItems.register("iron_spear", new CustomSpearItem(ToolMaterials.IRON, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item GOLDEN_DAGGER = CustomItems.register("golden_dagger", new CustomDaggerItem(ToolMaterials.GOLD, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item GOLDEN_GREATSWORD = CustomItems.register("golden_greatsword", new CustomGreatswordItem(ToolMaterials.GOLD, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item GOLDEN_MACE = CustomItems.register("golden_mace", new CustomMaceItem(ToolMaterials.GOLD, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item GOLDEN_SPEAR = CustomItems.register("golden_spear", new CustomSpearItem(ToolMaterials.GOLD, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item DIAMOND_DAGGER = CustomItems.register("diamond_dagger", new CustomDaggerItem(ToolMaterials.DIAMOND, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item DIAMOND_GREATSWORD = CustomItems.register("diamond_greatsword", new CustomGreatswordItem(ToolMaterials.DIAMOND, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item DIAMOND_MACE = CustomItems.register("diamond_mace", new CustomMaceItem(ToolMaterials.DIAMOND, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item DIAMOND_SPEAR = CustomItems.register("diamond_spear", new CustomSpearItem(ToolMaterials.DIAMOND, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item NETHERITE_DAGGER = CustomItems.register("netherite_dagger", new CustomDaggerItem(ToolMaterials.NETHERITE, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item NETHERITE_GREATSWORD = CustomItems.register("netherite_greatsword", new CustomGreatswordItem(ToolMaterials.NETHERITE, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item NETHERITE_MACE = CustomItems.register("netherite_mace", new CustomMaceItem(ToolMaterials.NETHERITE, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item NETHERITE_SPEAR = CustomItems.register("netherite_spear", new CustomSpearItem(ToolMaterials.NETHERITE, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item GOLDEN_SHIELD = CustomItems.register("golden_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 60, 50, 0f, 0f, 0.1f, Items.GOLD_INGOT));
    public static final Item TURTLE_SHIELD = CustomItems.register("turtle_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(500).group(CustomItemGroup.ECFABRIC), 200, 15, 0f, 3f, 0f, Items.SCUTE));
    public static final Item TURTLE_CHESTPLATE = CustomItems.register("turtle_chestplate", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item TURTLE_LEGGINGS = CustomItems.register("turtle_leggings", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item TURTLE_BOOTS = CustomItems.register("turtle_boots", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));

    public static final Item COPPER_SWORD = CustomItems.register("copper_sword", new CustomSwordItem(CustomToolMaterials.COPPER, 3, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_SHOVEL = CustomItems.register("copper_shovel", new CustomShovelItem(CustomToolMaterials.COPPER, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_PICKAXE = CustomItems.register("copper_pickaxe", new CustomPickaxeItem(CustomToolMaterials.COPPER, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_AXE = CustomItems.register("copper_axe", new CustomAxeItem(CustomToolMaterials.COPPER, 5, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_HOE = CustomItems.register("copper_hoe", new CustomHoeItem(CustomToolMaterials.COPPER, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_DAGGER = CustomItems.register("copper_dagger", new CustomDaggerItem(CustomToolMaterials.COPPER, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_GREATSWORD = CustomItems.register("copper_greatsword", new CustomGreatswordItem(CustomToolMaterials.COPPER, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_MACE = CustomItems.register("copper_mace", new CustomMaceItem(CustomToolMaterials.COPPER, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_SPEAR = CustomItems.register("copper_spear", new CustomSpearItem(CustomToolMaterials.COPPER, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_SHIELD = CustomItems.register("copper_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 60, 20, 0f, 0f, 0f, Items.COPPER_INGOT));
    public static final Item COPPER_HELMET = CustomItems.register("copper_helmet", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_CHESTPLATE = CustomItems.register("copper_chestplate", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_LEGGINGS = CustomItems.register("copper_leggings", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_BOOTS = CustomItems.register("copper_boots", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_COG = CustomItems.register("copper_cog", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.ECFABRIC),
                                                ImmutableMultimap.of(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(String.format("%s:movement_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                                                ));

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
    public static final Item AMETHYST_SHIELD = CustomItems.register("amethyst_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.ECFABRIC), 100, 22, 0f, 2f, 0f, Items.AMETHYST_SHARD));
    public static final Item AMETHYST_HELMET = CustomItems.register("amethyst_helmet", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_CHESTPLATE = CustomItems.register("amethyst_chestplate", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_LEGGINGS = CustomItems.register("amethyst_leggings", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_BOOTS = CustomItems.register("amethyst_boots", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_AMULET = CustomItems.register("amethyst_amulet", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.ECFABRIC),
                                                ImmutableMultimap.of(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(String.format("%s:attack_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
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
    public static final Item STEEL_SHIELD = CustomItems.register("steel_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(400).group(CustomItemGroup.ECFABRIC), 200, 15, 2f, 0f, 0f, CustomItems.STEEL_INGOT));
    public static final Item STEEL_HELMET = CustomItems.register("steel_helmet", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_CHESTPLATE = CustomItems.register("steel_chestplate", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_LEGGINGS = CustomItems.register("steel_leggings", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_BOOTS = CustomItems.register("steel_boots", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_LINK = CustomItems.register("steel_link", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.ECFABRIC),
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
    public static final Item BONE_SHIELD = CustomItems.register("bone_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(250).group(CustomItemGroup.ECFABRIC), 200, 30, 0f, 0f, 0.05f, Items.BONE));
    public static final Item BONE_HELMET = CustomItems.register("bone_helmet", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_CHESTPLATE = CustomItems.register("bone_chestplate", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_LEGGINGS = CustomItems.register("bone_leggings", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_BOOTS = CustomItems.register("bone_boots", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_IDOL = CustomItems.register("bone_idol", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.ECFABRIC),
                                            ImmutableMultimap.of(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(String.format("%s:health", EternalCraft.Namespace), 4f, EntityAttributeModifier.Operation.ADDITION))
                                            ));

    public static final Item OBSIDIAN_SWORD = CustomItems.register("obsidian_sword", new CustomSwordItem(CustomToolMaterials.OBSIDIAN, 3, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_SHOVEL = CustomItems.register("obsidian_shovel", new CustomShovelItem(CustomToolMaterials.OBSIDIAN, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_PICKAXE = CustomItems.register("obsidian_pickaxe", new CustomPickaxeItem(CustomToolMaterials.OBSIDIAN, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_AXE = CustomItems.register("obsidian_axe", new CustomAxeItem(CustomToolMaterials.OBSIDIAN, 5, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_HOE = CustomItems.register("obsidian_hoe", new CustomHoeItem(CustomToolMaterials.OBSIDIAN, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_DAGGER = CustomItems.register("obsidian_dagger", new CustomDaggerItem(CustomToolMaterials.OBSIDIAN, 0, -1.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_GREATSWORD = CustomItems.register("obsidian_greatsword", new CustomGreatswordItem(CustomToolMaterials.OBSIDIAN, 4, -3.0f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_MACE = CustomItems.register("obsidian_mace", new CustomMaceItem(CustomToolMaterials.OBSIDIAN, 2, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_SPEAR = CustomItems.register("obsidian_spear", new CustomSpearItem(CustomToolMaterials.OBSIDIAN, 1, -2.4f, new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item OBSIDIAN_PEARL = CustomItems.register("obsidian_pearl", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.ECFABRIC),
                                            ImmutableMultimap.of(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(String.format("%s:armor", EternalCraft.Namespace), 4f, EntityAttributeModifier.Operation.ADDITION))
                                            ));
    
    public static final Item WOODEN_STAFF = CustomItems.register("wooden_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item BONE_STAFF = CustomItems.register("bone_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item COPPER_STAFF = CustomItems.register("copper_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item TIME_STAFF = CustomItems.register("time_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item EMERALD_STAFF = CustomItems.register("emerald_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item ENDER_STAFF = CustomItems.register("ender_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STEEL_STAFF = CustomItems.register("steel_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item AMETHYST_STAFF = CustomItems.register("amethyst_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item SOUL_STAFF = CustomItems.register("soul_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item FIRE_STAFF = CustomItems.register("fire_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item SEA_STAFF = CustomItems.register("sea_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item NETHERITE_STAFF = CustomItems.register("netherite_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));
    public static final Item STAR_STAFF = CustomItems.register("star_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.ECFABRIC)));

    
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
