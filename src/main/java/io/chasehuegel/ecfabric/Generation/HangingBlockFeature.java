package io.chasehuegel.ecfabric.Generation;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class HangingBlockFeature extends Feature<HangingBlockFeatureConfig> {
    public HangingBlockFeature(Codec<HangingBlockFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<HangingBlockFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        HangingBlockFeatureConfig config = context.getConfig();

        int spread = config.spread().get(context.getRandom());
        float density = config.density().get(context.getRandom());
        int height = config.height().get(context.getRandom());

        for (int x = -spread; x < spread; x++) {
            for (int y = -spread; y < spread; y++) {
                for (int z = -spread; z < spread; z++) {
                    int distance = Math.abs(x) + Math.abs(y) + Math.abs(z);
                    if (distance <= spread && context.getRandom().nextFloat() <= density) {
                        BlockPos pos = origin.add(x, y, z);

                        if (context.getWorld().getBlockState(pos).isFullCube(context.getWorld(), pos)) {
                            for (int i = 0; i < height; i++) {
                                if (!context.getWorld().getBlockState(pos.down(i+1)).isAir()) {
                                    return true;
                                }
                            }
                            
                            context.getWorld().setBlockState(pos.down(), config.block().getBlockState(context.getRandom(), pos), 3);
                        }
                    }
                }
            }
        }

        return true;
    }
}
