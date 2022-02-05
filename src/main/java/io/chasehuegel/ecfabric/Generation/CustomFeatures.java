package io.chasehuegel.ecfabric.Generation;

import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class CustomFeatures {
    public static final Feature<HangingBlockFeatureConfig> HANGING_BLOCK = CustomFeatures.register("hanging_block", new HangingBlockFeature(HangingBlockFeatureConfig.CODEC));
    public static final Feature<SurfacePatchFeatureConfig> SURFACE_PATCH = CustomFeatures.register("surface_patch", new SurfacePatchFeature(SurfacePatchFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return (F)Registry.register(Registry.FEATURE, new Identifier(EternalCraft.Namespace, name), feature);
    }
}
