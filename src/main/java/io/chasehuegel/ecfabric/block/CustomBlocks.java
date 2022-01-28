package io.chasehuegel.ecfabric.block;

import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
    
    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(EternalCraft.Namespace, id), block);
    }

    public static void Initialize() {}
}
