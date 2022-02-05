package io.chasehuegel.ecfabric.Generation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record SurfacePatchFeatureConfig(RuleTest rule, IntProvider spread, FloatProvider density, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<SurfacePatchFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        RuleTest.TYPE_CODEC.fieldOf("rule").forGetter(SurfacePatchFeatureConfig::rule),
        IntProvider.VALUE_CODEC.fieldOf("spread").forGetter(SurfacePatchFeatureConfig::spread),
        FloatProvider.VALUE_CODEC.fieldOf("density").forGetter(SurfacePatchFeatureConfig::density),
        BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(SurfacePatchFeatureConfig::block)
    ).apply(instance, instance.stable(SurfacePatchFeatureConfig::new)));
}