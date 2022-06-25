package io.chasehuegel.ecfabric;

import io.chasehuegel.ecfabric.Generation.CustomFeatures;
import io.chasehuegel.ecfabric.Generation.HangingBlockFeatureConfig;
import io.chasehuegel.ecfabric.Generation.SurfacePatchFeatureConfig;
import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItemGroup;
import io.chasehuegel.ecfabric.item.CustomItems;
import io.chasehuegel.ecfabric.item.ItemEvents;
import io.chasehuegel.ecfabric.magic.SpellManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class EternalCraft implements ModInitializer {
	public static final String Namespace = "ecfabric";

	public static final Logger LOGGER = LogManager.getLogger(Namespace);

	public static final Random Random = new Random();
	public static final net.minecraft.util.math.random.Random MinecraftRandom = net.minecraft.util.math.random.Random.create();

	public static final Identifier SPELL_RELOAD_PACKET = new Identifier(EternalCraft.Namespace, "spell_reload");
	public static final Identifier SPELL_DATA_PACKET = new Identifier(EternalCraft.Namespace, "spell_data");
	public static final Identifier SPELL_CAST_PACKET = new Identifier(EternalCraft.Namespace, "spell_cast");
	public static final Identifier SPELL_HIT_PACKET = new Identifier(EternalCraft.Namespace, "spell_hit");

	private static RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PLATINUM_ORE_CONFIG;
	public static RegistryEntry<PlacedFeature> PLATINUM_ORE_PLACED;

	public static RegistryEntry<ConfiguredFeature<HangingBlockFeatureConfig, ?>> ICICLES_CONFIG;
	public static RegistryEntry<PlacedFeature> ICICLES_PLACED;

	public static RegistryEntry<ConfiguredFeature<SurfacePatchFeatureConfig, ?>> FROST_CONFIG;
	public static RegistryEntry<PlacedFeature> FROST_PLACED;

	public static RegistryEntry<ConfiguredFeature<HangingBlockFeatureConfig, ?>> HANGING_MOSS_CONFIG;
	public static RegistryEntry<PlacedFeature> HANGING_MOSS_PLACED;

	public static RegistryEntry<ConfiguredFeature<SurfacePatchFeatureConfig, ?>> MOSS_CONFIG;
	public static RegistryEntry<PlacedFeature> MOSS_PLACED;

	public static RegistryEntry<ConfiguredFeature<SurfacePatchFeatureConfig, ?>> FUNGUS_CONFIG;
	public static RegistryEntry<PlacedFeature> FUNGUS_PLACED;
	
	public static RegistryEntry<ConfiguredFeature<SurfacePatchFeatureConfig, ?>> CLOVER_CONFIG;
	public static RegistryEntry<PlacedFeature> CLOVER_PLACED;

	public static RegistryEntry<ConfiguredFeature<SurfacePatchFeatureConfig, ?>> FLOWERING_CONFIG;
	public static RegistryEntry<PlacedFeature> FLOWERING_PLACED;

	@Override
	public void onInitialize() {
		CustomBlocks.Initialize();
		CustomItems.Initialize();
		CustomItemGroup.Initialize();
		ItemEvents.Initialize();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SpellManager());

		//	PLATINUM ORE
		PLATINUM_ORE_CONFIG = ConfiguredFeatures.register("platinum_ore", Feature.ORE, new OreFeatureConfig(
			OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
			CustomBlocks.PLATINUM_ORE.getDefaultState(),
			4));
		PLATINUM_ORE_PLACED = PlacedFeatures.register("platinum_ore",
			PLATINUM_ORE_CONFIG,
			CountPlacementModifier.of(1),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.aboveBottom(60)));
		BiomeModifications.addFeature(
			BiomeSelectors.foundInOverworld(),
			GenerationStep.Feature.UNDERGROUND_ORES,
			PLATINUM_ORE_PLACED.getKey().get());

		//	ICICLES
		ICICLES_CONFIG = ConfiguredFeatures.register("icicles", CustomFeatures.HANGING_BLOCK, new HangingBlockFeatureConfig(
				UniformIntProvider.create(4, 8),
				UniformFloatProvider.create(0.3f, 0.5f),
				ConstantIntProvider.create(1),
				BlockStateProvider.of(CustomBlocks.ICICLES)));
		ICICLES_PLACED = PlacedFeatures.register("icicles",
				ICICLES_CONFIG,
				CountPlacementModifier.of(30),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE),
				GenerationStep.Feature.VEGETAL_DECORATION,
				ICICLES_PLACED.getKey().get());

		//	FROST
		FROST_CONFIG = ConfiguredFeatures.register("frost", CustomFeatures.SURFACE_PATCH, new SurfacePatchFeatureConfig(
				new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
				UniformIntProvider.create(8, 16),
				ConstantFloatProvider.create(1f),
				BlockStateProvider.of(CustomBlocks.FROST)));
		FROST_PLACED = PlacedFeatures.register("frost",
				FROST_CONFIG,
				CountPlacementModifier.of(30),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE),
				GenerationStep.Feature.VEGETAL_DECORATION,
				FROST_PLACED.getKey().get());

		//	HANGING MOSS
		HANGING_MOSS_CONFIG = ConfiguredFeatures.register("hanging_moss", CustomFeatures.HANGING_BLOCK, new HangingBlockFeatureConfig(
				UniformIntProvider.create(1, 4),
				UniformFloatProvider.create(0.4f, 0.75f),
				ConstantIntProvider.create(2),
				BlockStateProvider.of(CustomBlocks.HANGING_MOSS)));
		HANGING_MOSS_PLACED = PlacedFeatures.register("hanging_moss",
				HANGING_MOSS_CONFIG,
				CountPlacementModifier.of(30),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
						.or(BiomeSelectors.tag(BiomeTags.IS_TAIGA))
						.or(BiomeSelectors.tag(BiomeTags.IS_RIVER))
						.or(BiomeSelectors.tag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))
						.or(BiomeSelectors.tag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE)),
				GenerationStep.Feature.VEGETAL_DECORATION,
				HANGING_MOSS_PLACED.getKey().get());

		//	MOSS
		MOSS_CONFIG = ConfiguredFeatures.register("moss", CustomFeatures.SURFACE_PATCH, new SurfacePatchFeatureConfig(
				new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
				UniformIntProvider.create(0, 4),
				ConstantFloatProvider.create(1f),
				BlockStateProvider.of(CustomBlocks.MOSS)));
		MOSS_PLACED = PlacedFeatures.register("moss",
				MOSS_CONFIG,
				CountPlacementModifier.of(30),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
						.or(BiomeSelectors.tag(BiomeTags.IS_TAIGA))
						.or(BiomeSelectors.tag(BiomeTags.IS_RIVER))
						.or(BiomeSelectors.tag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))
						.or(BiomeSelectors.tag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE)),
				GenerationStep.Feature.VEGETAL_DECORATION,
				MOSS_PLACED.getKey().get());

		//	FUNGUS
		FUNGUS_CONFIG = ConfiguredFeatures.register("fungus", CustomFeatures.SURFACE_PATCH, new SurfacePatchFeatureConfig(
				new TagMatchRuleTest(BlockTags.MOSS_REPLACEABLE),
				UniformIntProvider.create(0, 4),
				UniformFloatProvider.create(0.1f, 0.3f),
				BlockStateProvider.of(CustomBlocks.FUNGUS)));
		FUNGUS_PLACED = PlacedFeatures.register("fungus",
				FUNGUS_CONFIG,
				CountPlacementModifier.of(30),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
						.or(BiomeSelectors.tag(BiomeTags.IS_TAIGA))
						.or(BiomeSelectors.tag(BiomeTags.IS_RIVER))
						.or(BiomeSelectors.tag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS))
						.or(BiomeSelectors.tag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE)),
				GenerationStep.Feature.VEGETAL_DECORATION,
				FUNGUS_PLACED.getKey().get());

		//	CLOVER
		CLOVER_CONFIG = ConfiguredFeatures.register("clover", CustomFeatures.SURFACE_PATCH, new SurfacePatchFeatureConfig(
				new TagMatchRuleTest(BlockTags.DIRT),
				UniformIntProvider.create(0, 6),
				UniformFloatProvider.create(0.1f, 0.3f),
				BlockStateProvider.of(CustomBlocks.CLOVER)));
		CLOVER_PLACED = PlacedFeatures.register("clover",
				CLOVER_CONFIG,
				CountPlacementModifier.of(10),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
						.or(BiomeSelectors.tag(BiomeTags.IS_RIVER))
						.or(BiomeSelectors.tag(BiomeTags.IS_FOREST)),
				GenerationStep.Feature.VEGETAL_DECORATION,
				CLOVER_PLACED.getKey().get());

		//	FLOWERING
		FLOWERING_CONFIG = ConfiguredFeatures.register("flowering", CustomFeatures.SURFACE_PATCH, new SurfacePatchFeatureConfig(
				new TagMatchRuleTest(BlockTags.DIRT),
				UniformIntProvider.create(0, 6),
				UniformFloatProvider.create(0.1f, 0.3f),
				BlockStateProvider.of(CustomBlocks.FLOWER_COVER)));
		FLOWERING_PLACED = PlacedFeatures.register("flowering",
				FLOWERING_CONFIG,
				CountPlacementModifier.of(10),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.fixed(60), YOffset.getTop()));
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE)
						.or(BiomeSelectors.tag(BiomeTags.IS_FOREST)),
				GenerationStep.Feature.VEGETAL_DECORATION,
				FLOWERING_PLACED.getKey().get());
	}
}
