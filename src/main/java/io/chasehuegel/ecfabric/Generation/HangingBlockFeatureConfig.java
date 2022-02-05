package io.chasehuegel.ecfabric.Generation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record HangingBlockFeatureConfig(IntProvider spread, FloatProvider density, IntProvider height, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<HangingBlockFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        IntProvider.VALUE_CODEC.fieldOf("spread").forGetter(HangingBlockFeatureConfig::spread),
        FloatProvider.VALUE_CODEC.fieldOf("density").forGetter(HangingBlockFeatureConfig::density),
        IntProvider.VALUE_CODEC.fieldOf("height").forGetter(HangingBlockFeatureConfig::height),
        BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(HangingBlockFeatureConfig::block)
    ).apply(instance, instance.stable(HangingBlockFeatureConfig::new)));
}