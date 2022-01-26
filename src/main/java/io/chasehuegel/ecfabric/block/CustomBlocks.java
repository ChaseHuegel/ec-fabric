package io.chasehuegel.ecfabric.block;

import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomBlocks {
    public static final Block PLATINUM_ORE = CustomBlocks.register("platinum_ore", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(30.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block PLATINUM_BLOCK = CustomBlocks.register("platinum_block", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.BLACK).requiresTool().strength(50.0f, 1200.0f).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(EternalCraft.Namespace, id), block);
    }

    public static void Initialize() {}
}
