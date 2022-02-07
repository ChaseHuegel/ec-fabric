package io.chasehuegel.ecfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
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
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.chasehuegel.ecfabric.Generation.SurfacePatchFeatureConfig;
import io.chasehuegel.ecfabric.Generation.CustomFeatures;
import io.chasehuegel.ecfabric.Generation.HangingBlockFeatureConfig;
import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItemGroup;
import io.chasehuegel.ecfabric.item.CustomItems;
import io.chasehuegel.ecfabric.item.ItemEvents;

public class EternalCraft implements ModInitializer {
	public static final String Namespace = "ecfabric";

	public static final Logger LOGGER = LogManager.getLogger(Namespace);

	public static final Random Random = new Random();

	private static ConfiguredFeature<?, ?> PLATINUM_ORE_CONFIG = Feature.ORE.configure(new OreFeatureConfig(
		OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
		CustomBlocks.PLATINUM_ORE.getDefaultState(),
		4));
	public static PlacedFeature PLATINUM_ORE_PLACED = PLATINUM_ORE_CONFIG.withPlacement(
		CountPlacementModifier.of(1),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(0)));

	public static final ConfiguredFeature<?, ?> ICICLES_CONFIG = CustomFeatures.HANGING_BLOCK.configure(new HangingBlockFeatureConfig(
		UniformIntProvider.create(4, 8),
		UniformFloatProvider.create(0.3f, 0.5f),
		ConstantIntProvider.create(1),
		BlockStateProvider.of(CustomBlocks.ICICLES)));
	public static PlacedFeature ICICLES_PLACED = ICICLES_CONFIG.withPlacement(
		CountPlacementModifier.of(30),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));

	public static final ConfiguredFeature<?, ?> FROST_CONFIG = CustomFeatures.SURFACE_PATCH.configure(new SurfacePatchFeatureConfig(
		new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
		UniformIntProvider.create(8, 16),
		ConstantFloatProvider.create(1f),
		BlockStateProvider.of(CustomBlocks.FROST)));
	public static PlacedFeature FROST_PLACED = FROST_CONFIG.withPlacement(
		CountPlacementModifier.of(30),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));

	public static final ConfiguredFeature<?, ?> HANGING_MOSS_CONFIG = CustomFeatures.HANGING_BLOCK.configure(new HangingBlockFeatureConfig(
		UniformIntProvider.create(1, 4),
		UniformFloatProvider.create(0.4f, 0.75f),
		ConstantIntProvider.create(2),
		BlockStateProvider.of(CustomBlocks.HANGING_MOSS)));
	public static PlacedFeature HANGING_MOSS_PLACED = HANGING_MOSS_CONFIG.withPlacement(
		CountPlacementModifier.of(40),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));

	public static final ConfiguredFeature<?, ?> MOSS_CONFIG = CustomFeatures.SURFACE_PATCH.configure(new SurfacePatchFeatureConfig(
		new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
		UniformIntProvider.create(0, 4),
		ConstantFloatProvider.create(1f),
		BlockStateProvider.of(CustomBlocks.MOSS)));
	public static PlacedFeature MOSS_PLACED = MOSS_CONFIG.withPlacement(
		CountPlacementModifier.of(30),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));

	public static final ConfiguredFeature<?, ?> FUNGUS_CONFIG = CustomFeatures.SURFACE_PATCH.configure(new SurfacePatchFeatureConfig(
		new TagMatchRuleTest(BlockTags.MOSS_REPLACEABLE),
		UniformIntProvider.create(0, 4),
		UniformFloatProvider.create(0.1f, 0.3f),
		BlockStateProvider.of(CustomBlocks.FUNGUS)));
	public static PlacedFeature FUNGUS_PLACED = FUNGUS_CONFIG.withPlacement(
		CountPlacementModifier.of(30),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
	
	public static final ConfiguredFeature<?, ?> CLOVER_CONFIG = CustomFeatures.SURFACE_PATCH.configure(new SurfacePatchFeatureConfig(
		new TagMatchRuleTest(BlockTags.DIRT),
		UniformIntProvider.create(0, 6),
		UniformFloatProvider.create(0.1f, 0.3f),
		BlockStateProvider.of(CustomBlocks.CLOVER)));
	public static PlacedFeature CLOVER_PLACED = CLOVER_CONFIG.withPlacement(
		CountPlacementModifier.of(10),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.getTop()));

	public static final ConfiguredFeature<?, ?> FLOWERING_CONFIG = CustomFeatures.SURFACE_PATCH.configure(new SurfacePatchFeatureConfig(
		new TagMatchRuleTest(BlockTags.DIRT),
		UniformIntProvider.create(0, 6),
		UniformFloatProvider.create(0.1f, 0.3f),
		BlockStateProvider.of(CustomBlocks.FLOWER_COVER)));
	public static PlacedFeature FLOWERING_PLACED = FLOWERING_CONFIG.withPlacement(
		CountPlacementModifier.of(10),
		SquarePlacementModifier.of(),
		HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.getTop()));

	@Override
	public void onInitialize() {
		CustomBlocks.Initialize();
		CustomItems.Initialize();
		CustomItemGroup.Initialize();
		ItemEvents.Initialize();

		var platinum_ore = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "overworld_platinum_ore"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, platinum_ore.getValue(), PLATINUM_ORE_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, platinum_ore.getValue(), PLATINUM_ORE_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, platinum_ore);
		
		var icicles = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "icicles"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, icicles.getValue(), ICICLES_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, icicles.getValue(), ICICLES_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.VEGETAL_DECORATION, icicles);
		
		var frost = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "frost"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, frost.getValue(), FROST_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, frost.getValue(), FROST_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.VEGETAL_DECORATION, frost);

		var hanging_moss = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "hanging_moss"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, hanging_moss.getValue(), HANGING_MOSS_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, hanging_moss.getValue(), HANGING_MOSS_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.UNDERGROUND, Biome.Category.SWAMP, Biome.Category.RIVER, Biome.Category.TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, hanging_moss);

		var moss = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "moss"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, moss.getValue(), MOSS_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, moss.getValue(), MOSS_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.UNDERGROUND, Biome.Category.SWAMP, Biome.Category.RIVER, Biome.Category.TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, moss);
		
		var fungus = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "fungus"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, fungus.getValue(), FUNGUS_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, fungus.getValue(), FUNGUS_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.UNDERGROUND, Biome.Category.SWAMP, Biome.Category.MUSHROOM, Biome.Category.TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, fungus);
		
		var clover = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "clover"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, clover.getValue(), CLOVER_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, clover.getValue(), CLOVER_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP, Biome.Category.RIVER, Biome.Category.FOREST), GenerationStep.Feature.VEGETAL_DECORATION, clover);
		
		var flowering = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(EternalCraft.Namespace, "flowering"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, flowering.getValue(), FLOWERING_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, flowering.getValue(), FLOWERING_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST, Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, flowering);
	}
}
