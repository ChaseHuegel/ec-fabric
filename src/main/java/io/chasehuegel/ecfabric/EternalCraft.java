package io.chasehuegel.ecfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItemGroup;
import io.chasehuegel.ecfabric.item.CustomItems;

public class EternalCraft implements ModInitializer {
	public static final String Namespace = "ecfabric";

	public static final Logger LOGGER = LogManager.getLogger(Namespace);

	public static final Random Random = new Random();

	private static ConfiguredFeature<?, ?> OVERWORLD_PLATINUM_ORE_CONFIGURED_FEATURE = Feature.ORE
		.configure(new OreFeatureConfig(
		OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
		CustomBlocks.PLATINUM_ORE.getDefaultState(),
		4)); // vein size

	public static PlacedFeature OVERWORLD_PLATINUM_ORE_PLACED_FEATURE = OVERWORLD_PLATINUM_ORE_CONFIGURED_FEATURE.withPlacement(
		CountPlacementModifier.of(1), // number of veins per chunk
		SquarePlacementModifier.of(), // spreading horizontally
		HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(0))); // height

	@Override
	public void onInitialize() {
		CustomBlocks.Initialize();
		CustomItems.Initialize();
		CustomItemGroup.Initialize();

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(EternalCraft.Namespace, "overworld_platinum_ore"), OVERWORLD_PLATINUM_ORE_CONFIGURED_FEATURE);

		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(EternalCraft.Namespace, "overworld_platinum_ore"), OVERWORLD_PLATINUM_ORE_PLACED_FEATURE);

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "overworld_platinum_ore")));
	}
}
