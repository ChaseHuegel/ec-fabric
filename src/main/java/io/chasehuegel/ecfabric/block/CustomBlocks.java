package io.chasehuegel.ecfabric.block;

import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomBlocks {
    public static final Block PLATINUM_ORE = CustomBlocks.register("platinum_ore", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(30.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block PLATINUM_BLOCK = CustomBlocks.register("platinum_block", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block PLATINUM_BRICKS = CustomBlocks.register("platinum_bricks", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block PLATINUM_PILLAR = CustomBlocks.register("platinum_pillar", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block STEEL_BLOCK = CustomBlocks.register("steel_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block IRON_BRICKS = CustomBlocks.register("iron_bricks", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block IRON_PILLAR = CustomBlocks.register("iron_pillar", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block GOLD_BRICKS = CustomBlocks.register("gold_bricks", new Block(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK)));
    public static final Block GOLD_PILLAR = CustomBlocks.register("gold_pillar", new Block(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK)));
    public static final Block DIAMOND_BRICKS = CustomBlocks.register("diamond_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)));
    public static final Block DIAMOND_PILLAR = CustomBlocks.register("diamond_pillar", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)));
    public static final Block NETHERITE_BRICKS = CustomBlocks.register("netherite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));
    public static final Block NETHERITE_PILLAR = CustomBlocks.register("netherite_pillar", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));
    public static final Block EMERALD_BRICKS = CustomBlocks.register("emerald_bricks", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK)));
    public static final Block EMERALD_PILLAR = CustomBlocks.register("emerald_pillar", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK)));
    
    public static final Block EASTERN_LAMP = CustomBlocks.register("eastern_lamp", new Block(AbstractBlock.Settings.copy(Blocks.GLOWSTONE).sounds(BlockSoundGroup.WOOD)));
    public static final Block PACKED_DIRT = CustomBlocks.register("packed_dirt", new Block(AbstractBlock.Settings.copy(Blocks.COARSE_DIRT)));
    public static final Block ANDESITE_BRICKS = CustomBlocks.register("andesite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block CHISELED_ANDESITE = CustomBlocks.register("chiseled_andesite", new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block CHISELED_STONE = CustomBlocks.register("chiseled_stone", new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
    public static final Block DIORITE_BRICKS = CustomBlocks.register("diorite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)));
    public static final Block CHISELED_DIORITE = CustomBlocks.register("chiseled_diorite", new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)));
    public static final Block CHISELED_GRANITE = CustomBlocks.register("chiseled_granite", new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)));
    public static final Block ACACIA_PLANKS_CARVED = CustomBlocks.register("acacia_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final Block OAK_PLANKS_CARVED = CustomBlocks.register("oak_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BIRCH_PLANKS_CARVED = CustomBlocks.register("birch_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final Block SPRUCE_PLANKS_CARVED = CustomBlocks.register("spruce_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final Block DARK_OAK_PLANKS_CARVED = CustomBlocks.register("dark_oak_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final Block JUNGLE_PLANKS_CARVED = CustomBlocks.register("jungle_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final Block CRIMSON_PLANKS_CARVED = CustomBlocks.register("crimson_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS)));
    public static final Block WARPED_PLANKS_CARVED = CustomBlocks.register("warped_planks_carved", new Block(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));
    public static final Block MUD = CustomBlocks.register("mud", new Block(AbstractBlock.Settings.copy(Blocks.SOUL_SAND).sounds(BlockSoundGroup.ROOTED_DIRT)));
    public static final Block MUD_BRICKS = CustomBlocks.register("mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = CustomBlocks.register("chiseled_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block WATTLE_AND_DAUB = CustomBlocks.register("wattle_and_daub", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CLAY_TILES = CustomBlocks.register("clay_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block FINE_GLASS = CustomBlocks.register("fine_glass", new GlassBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
    
    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(EternalCraft.Namespace, id), block);
    }

    public static void Initialize() {}
}
