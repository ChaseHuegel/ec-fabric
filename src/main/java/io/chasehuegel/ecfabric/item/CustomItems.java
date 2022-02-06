package io.chasehuegel.ecfabric.item;

import com.google.common.collect.ImmutableMultimap;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.block.CustomBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.BlockItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomItems {
    public static final int DAGGER_DAMAGE = 1;
    public static final float DAGGER_SPEED = -1f;
    public static final float DAGGER_RANGE = -0.5f;

    public static final int GREATSWORD_DAMAGE = 4;
    public static final float GREATSWORD_SPEED = -3f;
    public static final float GREATSWORD_RANGE = 0.5f;

    public static final int MACE_DAMAGE = 2;
    public static final float MACE_SPEED = -2f;
    public static final float MACE_RANGE = 0f;
    
    public static final int SPEAR_DAMAGE = 2;
    public static final float SPEAR_SPEED = -2.4f;
    public static final float SPEAR_RANGE = 1f;

    public static final Item EMPTY_CAN = CustomItems.register("empty_can", new WaterCan(Fluids.EMPTY, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    public static final Item WATER_CAN = CustomItems.register("water_can", new WaterCan(Fluids.WATER, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    public static final Item LAVA_CAN = CustomItems.register("lava_can", new WaterCan(Fluids.LAVA, new FabricItemSettings().group(CustomItemGroup.TOOLS).maxDamage(16)));
    
    public static final Item BO_STAFF = CustomItems.register("bo_staff", new CustomSpearItem(CustomToolMaterials.BAMBOO, 3, -2f, 1f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BLOWGUN = CustomItems.register("blowgun", new CrossbowItem(new FabricItemSettings().group(CustomItemGroup.COMBAT).maxCount(1)));
    
    public static final Item WOODEN_DAGGER = CustomItems.register("wooden_dagger", new CustomDaggerItem(ToolMaterials.WOOD, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WOODEN_GREATSWORD = CustomItems.register("wooden_greatsword", new CustomGreatswordItem(ToolMaterials.WOOD, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WOODEN_MACE = CustomItems.register("wooden_mace", new CustomMaceItem(ToolMaterials.WOOD, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WOODEN_SPEAR = CustomItems.register("wooden_spear", new CustomSpearItem(ToolMaterials.WOOD, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STONE_DAGGER = CustomItems.register("stone_dagger", new CustomDaggerItem(ToolMaterials.STONE, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STONE_GREATSWORD = CustomItems.register("stone_greatsword", new CustomGreatswordItem(ToolMaterials.STONE, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STONE_MACE = CustomItems.register("stone_mace", new CustomMaceItem(ToolMaterials.STONE, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STONE_SPEAR = CustomItems.register("stone_spear", new CustomSpearItem(ToolMaterials.STONE, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item IRON_DAGGER = CustomItems.register("iron_dagger", new CustomDaggerItem(ToolMaterials.IRON, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item IRON_GREATSWORD = CustomItems.register("iron_greatsword", new CustomGreatswordItem(ToolMaterials.IRON, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item IRON_MACE = CustomItems.register("iron_mace", new CustomMaceItem(ToolMaterials.IRON, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item IRON_SPEAR = CustomItems.register("iron_spear", new CustomSpearItem(ToolMaterials.IRON, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GOLDEN_DAGGER = CustomItems.register("golden_dagger", new CustomDaggerItem(ToolMaterials.GOLD, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GOLDEN_GREATSWORD = CustomItems.register("golden_greatsword", new CustomGreatswordItem(ToolMaterials.GOLD, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GOLDEN_MACE = CustomItems.register("golden_mace", new CustomMaceItem(ToolMaterials.GOLD, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GOLDEN_SPEAR = CustomItems.register("golden_spear", new CustomSpearItem(ToolMaterials.GOLD, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DIAMOND_DAGGER = CustomItems.register("diamond_dagger", new CustomDaggerItem(ToolMaterials.DIAMOND, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DIAMOND_GREATSWORD = CustomItems.register("diamond_greatsword", new CustomGreatswordItem(ToolMaterials.DIAMOND, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DIAMOND_MACE = CustomItems.register("diamond_mace", new CustomMaceItem(ToolMaterials.DIAMOND, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DIAMOND_SPEAR = CustomItems.register("diamond_spear", new CustomSpearItem(ToolMaterials.DIAMOND, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item NETHERITE_DAGGER = CustomItems.register("netherite_dagger", new CustomDaggerItem(ToolMaterials.NETHERITE, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item NETHERITE_GREATSWORD = CustomItems.register("netherite_greatsword", new CustomGreatswordItem(ToolMaterials.NETHERITE, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item NETHERITE_MACE = CustomItems.register("netherite_mace", new CustomMaceItem(ToolMaterials.NETHERITE, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item NETHERITE_SPEAR = CustomItems.register("netherite_spear", new CustomSpearItem(ToolMaterials.NETHERITE, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GOLDEN_SHIELD = CustomItems.register("golden_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.COMBAT), 60, 50, 0f, 0f, 0.1f, Items.GOLD_INGOT));
    public static final Item TURTLE_SHIELD = CustomItems.register("turtle_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(500).group(CustomItemGroup.COMBAT), 200, 15, 0f, 3f, 0f, Items.SCUTE));
    public static final Item TURTLE_CHESTPLATE = CustomItems.register("turtle_chestplate", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item TURTLE_LEGGINGS = CustomItems.register("turtle_leggings", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item TURTLE_BOOTS = CustomItems.register("turtle_boots", new ArmorItem(ArmorMaterials.TURTLE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));

    public static final Item COPPER_SWORD = CustomItems.register("copper_sword", new CustomSwordItem(CustomToolMaterials.COPPER, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_SHOVEL = CustomItems.register("copper_shovel", new CustomShovelItem(CustomToolMaterials.COPPER, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item COPPER_PICKAXE = CustomItems.register("copper_pickaxe", new CustomPickaxeItem(CustomToolMaterials.COPPER, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item COPPER_AXE = CustomItems.register("copper_axe", new CustomAxeItem(CustomToolMaterials.COPPER, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item COPPER_HOE = CustomItems.register("copper_hoe", new CustomHoeItem(CustomToolMaterials.COPPER, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item COPPER_DAGGER = CustomItems.register("copper_dagger", new CustomDaggerItem(CustomToolMaterials.COPPER, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_GREATSWORD = CustomItems.register("copper_greatsword", new CustomGreatswordItem(CustomToolMaterials.COPPER, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_MACE = CustomItems.register("copper_mace", new CustomMaceItem(CustomToolMaterials.COPPER, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_SPEAR = CustomItems.register("copper_spear", new CustomSpearItem(CustomToolMaterials.COPPER, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_SHIELD = CustomItems.register("copper_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.COMBAT), 60, 20, 0f, 1f, 0f, Items.COPPER_INGOT));
    public static final Item COPPER_HELMET = CustomItems.register("copper_helmet", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_CHESTPLATE = CustomItems.register("copper_chestplate", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_LEGGINGS = CustomItems.register("copper_leggings", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_BOOTS = CustomItems.register("copper_boots", new ArmorItem(CustomArmorMaterials.COPPER, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item AMETHYST_INGOT = CustomItems.register("amethyst_ingot", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item AMETHYST_SWORD = CustomItems.register("amethyst_sword", new CustomSwordItem(CustomToolMaterials.AMETHYST, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_SHOVEL = CustomItems.register("amethyst_shovel", new CustomShovelItem(CustomToolMaterials.AMETHYST, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_PICKAXE = CustomItems.register("amethyst_pickaxe", new CustomPickaxeItem(CustomToolMaterials.AMETHYST, 4, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_AXE = CustomItems.register("amethyst_axe", new CustomAxeItem(CustomToolMaterials.AMETHYST, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_HOE = CustomItems.register("amethyst_hoe", new CustomHoeItem(CustomToolMaterials.AMETHYST, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item AMETHYST_DAGGER = CustomItems.register("amethyst_dagger", new CustomDaggerItem(CustomToolMaterials.AMETHYST, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_GREATSWORD = CustomItems.register("amethyst_greatsword", new CustomGreatswordItem(CustomToolMaterials.AMETHYST, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_MACE = CustomItems.register("amethyst_mace", new CustomMaceItem(CustomToolMaterials.AMETHYST, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_SPEAR = CustomItems.register("amethyst_spear", new CustomSpearItem(CustomToolMaterials.AMETHYST, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_SHIELD = CustomItems.register("amethyst_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(200).group(CustomItemGroup.COMBAT), 100, 22, 0f, 2f, 0f, Items.AMETHYST_SHARD));
    public static final Item AMETHYST_HELMET = CustomItems.register("amethyst_helmet", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_CHESTPLATE = CustomItems.register("amethyst_chestplate", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_LEGGINGS = CustomItems.register("amethyst_leggings", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_BOOTS = CustomItems.register("amethyst_boots", new ArmorItem(CustomArmorMaterials.AMETHYST, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item STEEL_INGOT = CustomItems.register("steel_ingot", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item STEEL_SWORD = CustomItems.register("steel_sword", new CustomSwordItem(CustomToolMaterials.STEEL, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_SHOVEL = CustomItems.register("steel_shovel", new CustomShovelItem(CustomToolMaterials.STEEL, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item STEEL_PICKAXE = CustomItems.register("steel_pickaxe", new CustomPickaxeItem(CustomToolMaterials.STEEL, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item STEEL_AXE = CustomItems.register("steel_axe", new CustomAxeItem(CustomToolMaterials.STEEL, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item STEEL_HOE = CustomItems.register("steel_hoe", new CustomHoeItem(CustomToolMaterials.STEEL, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item STEEL_DAGGER = CustomItems.register("steel_dagger", new CustomDaggerItem(CustomToolMaterials.STEEL, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_GREATSWORD = CustomItems.register("steel_greatsword", new CustomGreatswordItem(CustomToolMaterials.STEEL, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_MACE = CustomItems.register("steel_mace", new CustomMaceItem(CustomToolMaterials.STEEL, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_SPEAR = CustomItems.register("steel_spear", new CustomSpearItem(CustomToolMaterials.STEEL, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_SHIELD = CustomItems.register("steel_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(400).group(CustomItemGroup.COMBAT), 200, 15, 1f, 0f, 0f, CustomItems.STEEL_INGOT));
    public static final Item STEEL_HELMET = CustomItems.register("steel_helmet", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_CHESTPLATE = CustomItems.register("steel_chestplate", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_LEGGINGS = CustomItems.register("steel_leggings", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_BOOTS = CustomItems.register("steel_boots", new ArmorItem(CustomArmorMaterials.STEEL, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item BONE_SWORD = CustomItems.register("bone_sword", new CustomSwordItem(CustomToolMaterials.BONE, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_SHOVEL = CustomItems.register("bone_shovel", new CustomShovelItem(CustomToolMaterials.BONE, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item BONE_PICKAXE = CustomItems.register("bone_pickaxe", new CustomPickaxeItem(CustomToolMaterials.BONE, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item BONE_AXE = CustomItems.register("bone_axe", new CustomAxeItem(CustomToolMaterials.BONE, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item BONE_HOE = CustomItems.register("bone_hoe", new CustomHoeItem(CustomToolMaterials.BONE, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item BONE_DAGGER = CustomItems.register("bone_dagger", new CustomDaggerItem(CustomToolMaterials.BONE, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_GREATSWORD = CustomItems.register("bone_greatsword", new CustomGreatswordItem(CustomToolMaterials.BONE, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_MACE = CustomItems.register("bone_mace", new CustomMaceItem(CustomToolMaterials.BONE, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_SPEAR = CustomItems.register("bone_spear", new CustomSpearItem(CustomToolMaterials.BONE, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_SHIELD = CustomItems.register("bone_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(250).group(CustomItemGroup.COMBAT), 200, 30, 0f, 0f, 0.05f, Items.BONE));
    public static final Item BONE_HELMET = CustomItems.register("bone_helmet", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_CHESTPLATE = CustomItems.register("bone_chestplate", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_LEGGINGS = CustomItems.register("bone_leggings", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_BOOTS = CustomItems.register("bone_boots", new ArmorItem(CustomArmorMaterials.BONE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item OBSIDIAN_SWORD = CustomItems.register("obsidian_sword", new CustomSwordItem(CustomToolMaterials.OBSIDIAN, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item OBSIDIAN_SHOVEL = CustomItems.register("obsidian_shovel", new CustomShovelItem(CustomToolMaterials.OBSIDIAN, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item OBSIDIAN_PICKAXE = CustomItems.register("obsidian_pickaxe", new CustomPickaxeItem(CustomToolMaterials.OBSIDIAN, 1, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item OBSIDIAN_AXE = CustomItems.register("obsidian_axe", new CustomAxeItem(CustomToolMaterials.OBSIDIAN, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item OBSIDIAN_HOE = CustomItems.register("obsidian_hoe", new CustomHoeItem(CustomToolMaterials.OBSIDIAN, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item OBSIDIAN_DAGGER = CustomItems.register("obsidian_dagger", new CustomDaggerItem(CustomToolMaterials.OBSIDIAN, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item OBSIDIAN_GREATSWORD = CustomItems.register("obsidian_greatsword", new CustomGreatswordItem(CustomToolMaterials.OBSIDIAN, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item OBSIDIAN_MACE = CustomItems.register("obsidian_mace", new CustomMaceItem(CustomToolMaterials.OBSIDIAN, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item OBSIDIAN_SPEAR = CustomItems.register("obsidian_spear", new CustomSpearItem(CustomToolMaterials.OBSIDIAN, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item COPPER_COG = CustomItems.register("copper_cog", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.TRINKETS),
    ImmutableMultimap.of(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(String.format("%s:movement_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
    ));
    public static final Item AMETHYST_AMULET = CustomItems.register("amethyst_amulet", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.TRINKETS),
    ImmutableMultimap.of(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(String.format("%s:attack_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
    ));
    public static final Item BONE_IDOL = CustomItems.register("bone_idol", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.TRINKETS),
    ImmutableMultimap.of(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(String.format("%s:health", EternalCraft.Namespace), 4f, EntityAttributeModifier.Operation.ADDITION))
    ));
    public static final Item STEEL_LINK = CustomItems.register("steel_link", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.TRINKETS),
    ImmutableMultimap.of(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(String.format("%s:toughness", EternalCraft.Namespace), 0.25f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
    ));
    public static final Item OBSIDIAN_PEARL = CustomItems.register("obsidian_pearl", new CustomTrinket(new FabricItemSettings().group(CustomItemGroup.TRINKETS),
    ImmutableMultimap.of(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(String.format("%s:armor", EternalCraft.Namespace), 4f, EntityAttributeModifier.Operation.ADDITION))
    ));
    
    public static final Item PLATINUM  = CustomItems.register("platinum", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item PLATINUM_INGOT  = CustomItems.register("platinum_ingot", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item PLATINUM_SWORD = CustomItems.register("platinum_sword", new CustomSwordItem(CustomToolMaterials.PLATINUM, 3, -2.4f, 0f, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_SHOVEL = CustomItems.register("platinum_shovel", new CustomShovelItem(CustomToolMaterials.PLATINUM, 1, -3.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item PLATINUM_PICKAXE = CustomItems.register("platinum_pickaxe", new CustomPickaxeItem(CustomToolMaterials.PLATINUM, 4, -2.8f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item PLATINUM_AXE = CustomItems.register("platinum_axe", new CustomAxeItem(CustomToolMaterials.PLATINUM, 5, -3.0f, 0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item PLATINUM_HOE = CustomItems.register("platinum_hoe", new CustomHoeItem(CustomToolMaterials.PLATINUM, -2, 0.0f, new FabricItemSettings().group(CustomItemGroup.TOOLS)));
    public static final Item PLATINUM_DAGGER = CustomItems.register("platinum_dagger", new CustomDaggerItem(CustomToolMaterials.PLATINUM, DAGGER_DAMAGE, DAGGER_SPEED, DAGGER_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_GREATSWORD = CustomItems.register("platinum_greatsword", new CustomGreatswordItem(CustomToolMaterials.PLATINUM, GREATSWORD_DAMAGE, GREATSWORD_SPEED, GREATSWORD_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_MACE = CustomItems.register("platinum_mace", new CustomMaceItem(CustomToolMaterials.PLATINUM, MACE_DAMAGE, MACE_SPEED, MACE_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_SPEAR = CustomItems.register("platinum_spear", new CustomSpearItem(CustomToolMaterials.PLATINUM, SPEAR_DAMAGE, SPEAR_SPEED, SPEAR_RANGE, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_SHIELD = CustomItems.register("platinum_shield", new CustomShieldItem(new FabricItemSettings().maxDamage(350).group(CustomItemGroup.COMBAT), 200, 25, 2f, 0f, 0f, CustomItems.PLATINUM));
    public static final Item PLATINUM_HELMET = CustomItems.register("platinum_helmet", new ArmorItem(CustomArmorMaterials.PLATINUM, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_CHESTPLATE = CustomItems.register("platinum_chestplate", new ArmorItem(CustomArmorMaterials.PLATINUM, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_LEGGINGS = CustomItems.register("platinum_leggings", new ArmorItem(CustomArmorMaterials.PLATINUM, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLATINUM_BOOTS = CustomItems.register("platinum_boots", new ArmorItem(CustomArmorMaterials.PLATINUM, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item WOODEN_STAFF = CustomItems.register("wooden_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BONE_STAFF = CustomItems.register("bone_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item COPPER_STAFF = CustomItems.register("copper_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item TIME_STAFF = CustomItems.register("time_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item EMERALD_STAFF = CustomItems.register("emerald_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ENDER_STAFF = CustomItems.register("ender_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STEEL_STAFF = CustomItems.register("steel_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AMETHYST_STAFF = CustomItems.register("amethyst_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SOUL_STAFF = CustomItems.register("soul_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FIRE_STAFF = CustomItems.register("fire_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SEA_STAFF = CustomItems.register("sea_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item NETHERITE_STAFF = CustomItems.register("netherite_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item STAR_STAFF = CustomItems.register("star_staff", new CustomStaffItem(new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item PLATINUM_ORE  = CustomItems.register("platinum_ore", new BlockItem(CustomBlocks.PLATINUM_ORE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PLATINUM_BLOCK  = CustomItems.register("platinum_block", new BlockItem(CustomBlocks.PLATINUM_BLOCK, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PLATINUM_BRICKS  = CustomItems.register("platinum_bricks", new BlockItem(CustomBlocks.PLATINUM_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PLATINUM_PILLAR  = CustomItems.register("platinum_pillar", new BlockItem(CustomBlocks.PLATINUM_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item STEEL_BLOCK  = CustomItems.register("steel_block", new BlockItem(CustomBlocks.STEEL_BLOCK, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item IRON_BRICKS  = CustomItems.register("iron_bricks", new BlockItem(CustomBlocks.IRON_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item IRON_PILLAR  = CustomItems.register("iron_pillar", new BlockItem(CustomBlocks.IRON_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item GOLD_BRICKS  = CustomItems.register("gold_bricks", new BlockItem(CustomBlocks.GOLD_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item GOLD_PILLAR  = CustomItems.register("gold_pillar", new BlockItem(CustomBlocks.GOLD_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item DIAMOND_BRICKS  = CustomItems.register("diamond_bricks", new BlockItem(CustomBlocks.DIAMOND_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item DIAMOND_PILLAR  = CustomItems.register("diamond_pillar", new BlockItem(CustomBlocks.DIAMOND_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item NETHERITE_BRICKS  = CustomItems.register("netherite_bricks", new BlockItem(CustomBlocks.NETHERITE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item NETHERITE_PILLAR  = CustomItems.register("netherite_pillar", new BlockItem(CustomBlocks.NETHERITE_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item EMERALD_BRICKS  = CustomItems.register("emerald_bricks", new BlockItem(CustomBlocks.EMERALD_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item EMERALD_PILLAR  = CustomItems.register("emerald_pillar", new BlockItem(CustomBlocks.EMERALD_PILLAR, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    
    public static final Item EASTERN_LAMP  = CustomItems.register("eastern_lamp", new BlockItem(CustomBlocks.EASTERN_LAMP, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PACKED_DIRT  = CustomItems.register("packed_dirt", new BlockItem(CustomBlocks.PACKED_DIRT, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    
    public static final Item ANDESITE_BRICKS  = CustomItems.register("andesite_bricks", new BlockItem(CustomBlocks.ANDESITE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_ANDESITE  = CustomItems.register("chiseled_andesite", new BlockItem(CustomBlocks.CHISELED_ANDESITE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_STONE  = CustomItems.register("chiseled_stone", new BlockItem(CustomBlocks.CHISELED_STONE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item DIORITE_BRICKS  = CustomItems.register("diorite_bricks", new BlockItem(CustomBlocks.DIORITE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_DIORITE  = CustomItems.register("chiseled_diorite", new BlockItem(CustomBlocks.CHISELED_DIORITE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_GRANITE  = CustomItems.register("chiseled_granite", new BlockItem(CustomBlocks.CHISELED_GRANITE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item ACACIA_PLANKS_CARVED  = CustomItems.register("acacia_planks_carved", new BlockItem(CustomBlocks.ACACIA_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item OAK_PLANKS_CARVED  = CustomItems.register("oak_planks_carved", new BlockItem(CustomBlocks.OAK_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item BIRCH_PLANKS_CARVED  = CustomItems.register("birch_planks_carved", new BlockItem(CustomBlocks.BIRCH_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item SPRUCE_PLANKS_CARVED  = CustomItems.register("spruce_planks_carved", new BlockItem(CustomBlocks.SPRUCE_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item DARK_OAK_PLANKS_CARVED  = CustomItems.register("dark_oak_planks_carved", new BlockItem(CustomBlocks.DARK_OAK_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item JUNGLE_PLANKS_CARVED  = CustomItems.register("jungle_planks_carved", new BlockItem(CustomBlocks.JUNGLE_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CRIMSON_PLANKS_CARVED  = CustomItems.register("crimson_planks_carved", new BlockItem(CustomBlocks.CRIMSON_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item WARPED_PLANKS_CARVED  = CustomItems.register("warped_planks_carved", new BlockItem(CustomBlocks.WARPED_PLANKS_CARVED, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item MUD  = CustomItems.register("mud", new BlockItem(CustomBlocks.MUD, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item MUD_BRICKS  = CustomItems.register("mud_bricks", new BlockItem(CustomBlocks.MUD_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_MUD_BRICKS  = CustomItems.register("chiseled_mud_bricks", new BlockItem(CustomBlocks.CHISELED_MUD_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item WATTLE_AND_DAUB  = CustomItems.register("wattle_and_daub", new BlockItem(CustomBlocks.WATTLE_AND_DAUB, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CLAY_TILES  = CustomItems.register("clay_tiles", new BlockItem(CustomBlocks.CLAY_TILES, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item FINE_GLASS  = CustomItems.register("fine_glass", new BlockItem(CustomBlocks.FINE_GLASS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item SNOW_BRICKS  = CustomItems.register("snow_bricks", new BlockItem(CustomBlocks.SNOW_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_SNOW_BRICKS  = CustomItems.register("chiseled_snow_bricks", new BlockItem(CustomBlocks.CHISELED_SNOW_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PACKED_SNOW  = CustomItems.register("packed_snow", new BlockItem(CustomBlocks.PACKED_SNOW, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item POLISHED_PACKED_ICE  = CustomItems.register("polished_packed_ice", new BlockItem(CustomBlocks.POLISHED_PACKED_ICE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item PACKED_ICE_BRICKS  = CustomItems.register("packed_ice_bricks", new BlockItem(CustomBlocks.PACKED_ICE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CHISELED_PACKED_ICE_BRICKS  = CustomItems.register("chiseled_packed_ice_bricks", new BlockItem(CustomBlocks.CHISELED_PACKED_ICE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item FROST  = CustomItems.register("frost", new BlockItem(CustomBlocks.FROST, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item GRANITE_BRICKS  = CustomItems.register("granite_bricks", new BlockItem(CustomBlocks.GRANITE_BRICKS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item ICICLES = CustomItems.register("icicles", new BlockItem(CustomBlocks.ICICLES, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item MOSS = CustomItems.register("moss", new BlockItem(CustomBlocks.MOSS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item HANGING_MOSS = CustomItems.register("hanging_moss", new BlockItem(CustomBlocks.HANGING_MOSS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item BASKET = CustomItems.register("basket", new BlockItem(CustomBlocks.BASKET, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item FUNGUS = CustomItems.register("fungus", new BlockItem(CustomBlocks.FUNGUS, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CLOVER = CustomItems.register("clover", new BlockItem(CustomBlocks.CLOVER, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item FLOWER_COVER = CustomItems.register("flower_cover", new BlockItem(CustomBlocks.FLOWER_COVER, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    
    public static final Item COPPER_VANE = CustomItems.register("copper_vane", new BlockItem(CustomBlocks.COPPER_VANE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item GLOBE = CustomItems.register("globe", new BlockItem(CustomBlocks.GLOBE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item LAMP = CustomItems.register("lamp", new BlockItem(CustomBlocks.LAMP, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item BAMBOO_BUNDLE = CustomItems.register("bamboo_bundle", new BlockItem(CustomBlocks.BAMBOO_BUNDLE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item SUGAR_CANE_BUNDLE = CustomItems.register("sugar_cane_bundle", new BlockItem(CustomBlocks.SUGAR_CANE_BUNDLE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item STICK_BUNDLE = CustomItems.register("stick_bundle", new BlockItem(CustomBlocks.STICK_BUNDLE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CACTUS_BUNDLE = CustomItems.register("cactus_bundle", new BlockItem(CustomBlocks.CACTUS_BUNDLE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CRATE = CustomItems.register("crate", new BlockItem(CustomBlocks.CRATE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item APPLE_BASKET = CustomItems.register("apple_basket", new BlockItem(CustomBlocks.APPLE_BASKET, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item BEET_CRATE = CustomItems.register("beet_crate", new BlockItem(CustomBlocks.BEET_CRATE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item BERRY_BASKET = CustomItems.register("berry_basket", new BlockItem(CustomBlocks.BERRY_BASKET, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item CARROT_CRATE = CustomItems.register("carrot_crate", new BlockItem(CustomBlocks.CARROT_CRATE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item FISH_CRATE = CustomItems.register("fish_crate", new BlockItem(CustomBlocks.FISH_CRATE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));
    public static final Item POTATO_CRATE = CustomItems.register("potato_crate", new BlockItem(CustomBlocks.POTATO_CRATE, new FabricItemSettings().group(CustomItemGroup.BLOCKS)));

    public static final Item THREAD  = CustomItems.register("thread", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item FABRIC  = CustomItems.register("fabric", new Item(new FabricItemSettings().group(CustomItemGroup.MISC)));
    public static final Item WIZARD_HELMET = CustomItems.register("wizard_helmet", new ArmorItem(CustomArmorMaterials.WIZARD, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WIZARD_CHESTPLATE = CustomItems.register("wizard_chestplate", new ArmorItem(CustomArmorMaterials.WIZARD, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WIZARD_LEGGINGS = CustomItems.register("wizard_leggings", new ArmorItem(CustomArmorMaterials.WIZARD, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item WIZARD_BOOTS = CustomItems.register("wizard_boots", new ArmorItem(CustomArmorMaterials.WIZARD, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ARMORER_HELMET = CustomItems.register("armorer_helmet", new ArmorItem(CustomArmorMaterials.ARMORER, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ARMORER_CHESTPLATE = CustomItems.register("armorer_chestplate", new ArmorItem(CustomArmorMaterials.ARMORER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item CLERIC_CHESTPLATE = CustomItems.register("cleric_chestplate", new ArmorItem(CustomArmorMaterials.CLERIC, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BUTCHER_CHESTPLATE = CustomItems.register("butcher_chestplate", new ArmorItem(CustomArmorMaterials.BUTCHER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DESERT_HELMET = CustomItems.register("desert_helmet", new ArmorItem(CustomArmorMaterials.DESERT, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DESERT_CHESTPLATE = CustomItems.register("desert_chestplate", new ArmorItem(CustomArmorMaterials.DESERT, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DESERT_LEGGINGS = CustomItems.register("desert_leggings", new ArmorItem(CustomArmorMaterials.DESERT, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item DESERT_BOOTS = CustomItems.register("desert_boots", new ArmorItem(CustomArmorMaterials.DESERT, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FISHERMAN_HELMET = CustomItems.register("fisherman_helmet", new ArmorItem(CustomArmorMaterials.FISHERMAN, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FISHERMAN_CHESTPLATE = CustomItems.register("fisherman_chestplate", new ArmorItem(CustomArmorMaterials.FISHERMAN, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item JUNGLE_CHESTPLATE = CustomItems.register("jungle_chestplate", new ArmorItem(CustomArmorMaterials.JUNGLE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item JUNGLE_LEGGINGS = CustomItems.register("jungle_leggings", new ArmorItem(CustomArmorMaterials.JUNGLE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item JUNGLE_BOOTS = CustomItems.register("jungle_boots", new ArmorItem(CustomArmorMaterials.JUNGLE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item MASON_CHESTPLATE = CustomItems.register("mason_chestplate", new ArmorItem(CustomArmorMaterials.MASON, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item LEATHERWORKER_CHESTPLATE = CustomItems.register("leatherworker_chestplate", new ArmorItem(CustomArmorMaterials.LEATHERWORKER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item LIBRARIAN_HELMET = CustomItems.register("librarian_helmet", new ArmorItem(CustomArmorMaterials.LIBRARIAN, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item LIBRARIAN_CHESTPLATE = CustomItems.register("librarian_chestplate", new ArmorItem(CustomArmorMaterials.LIBRARIAN, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLAINS_CHESTPLATE = CustomItems.register("plains_chestplate", new ArmorItem(CustomArmorMaterials.PLAINS, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLAINS_LEGGINGS = CustomItems.register("plains_leggings", new ArmorItem(CustomArmorMaterials.PLAINS, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PLAINS_BOOTS = CustomItems.register("plains_boots", new ArmorItem(CustomArmorMaterials.PLAINS, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SAVANNA_HELMET = CustomItems.register("savanna_helmet", new ArmorItem(CustomArmorMaterials.SAVANNA, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SAVANNA_CHESTPLATE = CustomItems.register("savanna_chestplate", new ArmorItem(CustomArmorMaterials.SAVANNA, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SAVANNA_LEGGINGS = CustomItems.register("savanna_leggings", new ArmorItem(CustomArmorMaterials.SAVANNA, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SAVANNA_BOOTS = CustomItems.register("savanna_boots", new ArmorItem(CustomArmorMaterials.SAVANNA, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SHEPHERD_HELMET = CustomItems.register("shepherd_helmet", new ArmorItem(CustomArmorMaterials.SHEPHERD, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SHEPHERD_CHESTPLATE = CustomItems.register("shepherd_chestplate", new ArmorItem(CustomArmorMaterials.SHEPHERD, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SNOW_HELMET = CustomItems.register("snow_helmet", new ArmorItem(CustomArmorMaterials.SNOW, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SNOW_CHESTPLATE = CustomItems.register("snow_chestplate", new ArmorItem(CustomArmorMaterials.SNOW, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SWAMP_HELMET = CustomItems.register("swamp_helmet", new ArmorItem(CustomArmorMaterials.SWAMP, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SWAMP_CHESTPLATE = CustomItems.register("swamp_chestplate", new ArmorItem(CustomArmorMaterials.SWAMP, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SWAMP_BOOTS = CustomItems.register("swamp_boots", new ArmorItem(CustomArmorMaterials.SWAMP, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item TAIGA_CHESTPLATE = CustomItems.register("taiga_chestplate", new ArmorItem(CustomArmorMaterials.TAIGA, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item TAIGA_BOOTS = CustomItems.register("taiga_boots", new ArmorItem(CustomArmorMaterials.TAIGA, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SACK_MASK = CustomItems.register("sack_mask", new ArmorItem(CustomArmorMaterials.SACK_MASK, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item SABITO_MASK = CustomItems.register("sabito_mask", new ArmorItem(CustomArmorMaterials.SABITO_MASK, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BANDANA = CustomItems.register("bandana", new ArmorItem(CustomArmorMaterials.BANDANA, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ANGEL_HELMET = CustomItems.register("angel_helmet", new ArmorItem(CustomArmorMaterials.ANGEL, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ANGEL_CHESTPLATE = CustomItems.register("angel_chestplate", new ArmorItem(CustomArmorMaterials.ANGEL, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ANGEL_LEGGINGS = CustomItems.register("angel_leggings", new ArmorItem(CustomArmorMaterials.ANGEL, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ANGEL_BOOTS = CustomItems.register("angel_boots", new ArmorItem(CustomArmorMaterials.ANGEL, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ATLANTIS_HELMET = CustomItems.register("atlantis_helmet", new ArmorItem(CustomArmorMaterials.ATLANTIS, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ATLANTIS_CHESTPLATE = CustomItems.register("atlantis_chestplate", new ArmorItem(CustomArmorMaterials.ATLANTIS, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ATLANTIS_LEGGINGS = CustomItems.register("atlantis_leggings", new ArmorItem(CustomArmorMaterials.ATLANTIS, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item ATLANTIS_BOOTS = CustomItems.register("atlantis_boots", new ArmorItem(CustomArmorMaterials.ATLANTIS, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AZALEA_HELMET = CustomItems.register("azalea_helmet", new ArmorItem(CustomArmorMaterials.AZALEA, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AZALEA_CHESTPLATE = CustomItems.register("azalea_chestplate", new ArmorItem(CustomArmorMaterials.AZALEA, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AZALEA_LEGGINGS = CustomItems.register("azalea_leggings", new ArmorItem(CustomArmorMaterials.AZALEA, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item AZALEA_BOOTS = CustomItems.register("azalea_boots", new ArmorItem(CustomArmorMaterials.AZALEA, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item CROWN = CustomItems.register("crown", new ArmorItem(CustomArmorMaterials.CROWN, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GRAY_WOLF_HELMET = CustomItems.register("gray_wolf_helmet", new ArmorItem(CustomArmorMaterials.GRAY_WOLF, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GRAY_WOLF_CHESTPLATE = CustomItems.register("gray_wolf_chestplate", new ArmorItem(CustomArmorMaterials.GRAY_WOLF, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GRAY_WOLF_LEGGINGS = CustomItems.register("gray_wolf_leggings", new ArmorItem(CustomArmorMaterials.GRAY_WOLF, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item GRAY_WOLF_BOOTS = CustomItems.register("gray_wolf_boots", new ArmorItem(CustomArmorMaterials.GRAY_WOLF, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FUR_HELMET = CustomItems.register("fur_helmet", new ArmorItem(CustomArmorMaterials.FUR, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FUR_CHESTPLATE = CustomItems.register("fur_chestplate", new ArmorItem(CustomArmorMaterials.FUR, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FUR_LEGGINGS = CustomItems.register("fur_leggings", new ArmorItem(CustomArmorMaterials.FUR, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item FUR_BOOTS = CustomItems.register("fur_boots", new ArmorItem(CustomArmorMaterials.FUR, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static final Item PILLAGER_HELMET = CustomItems.register("pillager_helmet", new ArmorItem(CustomArmorMaterials.PILLAGER, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PILLAGER_CHESTPLATE = CustomItems.register("pillager_chestplate", new ArmorItem(CustomArmorMaterials.PILLAGER, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PILLAGER_LEGGINGS = CustomItems.register("pillager_leggings", new ArmorItem(CustomArmorMaterials.PILLAGER, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PILLAGER_BOOTS = CustomItems.register("pillager_boots", new ArmorItem(CustomArmorMaterials.PILLAGER, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PIGLIN_HELMET = CustomItems.register("piglin_helmet", new ArmorItem(CustomArmorMaterials.PIGLIN, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PIGLIN_CHESTPLATE = CustomItems.register("piglin_chestplate", new ArmorItem(CustomArmorMaterials.PIGLIN, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PIGLIN_LEGGINGS = CustomItems.register("piglin_leggings", new ArmorItem(CustomArmorMaterials.PIGLIN, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PIGLIN_BOOTS = CustomItems.register("piglin_boots", new ArmorItem(CustomArmorMaterials.PIGLIN, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BLAZE_HELMET = CustomItems.register("blaze_helmet", new ArmorItem(CustomArmorMaterials.BLAZE, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BLAZE_CHESTPLATE = CustomItems.register("blaze_chestplate", new ArmorItem(CustomArmorMaterials.BLAZE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BLAZE_LEGGINGS = CustomItems.register("blaze_leggings", new ArmorItem(CustomArmorMaterials.BLAZE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item BLAZE_BOOTS = CustomItems.register("blaze_boots", new ArmorItem(CustomArmorMaterials.BLAZE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PRISMARINE_HELMET = CustomItems.register("prismarine_helmet", new ArmorItem(CustomArmorMaterials.PRISMARINE, EquipmentSlot.HEAD, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PRISMARINE_CHESTPLATE = CustomItems.register("prismarine_chestplate", new ArmorItem(CustomArmorMaterials.PRISMARINE, EquipmentSlot.CHEST, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PRISMARINE_LEGGINGS = CustomItems.register("prismarine_leggings", new ArmorItem(CustomArmorMaterials.PRISMARINE, EquipmentSlot.LEGS, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    public static final Item PRISMARINE_BOOTS = CustomItems.register("prismarine_boots", new ArmorItem(CustomArmorMaterials.PRISMARINE, EquipmentSlot.FEET, new FabricItemSettings().group(CustomItemGroup.COMBAT)));
    
    public static void Initialize() {}

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
