package io.chasehuegel.ecfabric.Generation;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SurfacePatchFeature extends Feature<SurfacePatchFeatureConfig> {
    public SurfacePatchFeature(Codec<SurfacePatchFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SurfacePatchFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        SurfacePatchFeatureConfig config = context.getConfig();

        int spread = config.spread().get(context.getRandom());
        float density = config.density().get(context.getRandom());

        for (int x = -spread; x < spread; x++) {
            for (int y = -spread; y < spread; y++) {
                for (int z = -spread; z < spread; z++) {
                    int distance = Math.abs(x) + Math.abs(y) + Math.abs(z);
                    if (distance <= spread && context.getRandom().nextFloat() <= density) {
                        BlockPos pos = origin.add(x, y, z);

                        if (config.rule().test(context.getWorld().getBlockState(pos), context.getRandom()) && context.getWorld().getBlockState(pos.up()).isAir()) {
                            context.getWorld().setBlockState(pos.up(), config.block().getBlockState(context.getRandom(), pos), 3);
                        }
                    }
                }
            }
        }

        return true;
    }
}
