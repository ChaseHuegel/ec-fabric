package io.chasehuegel.ecfabric.magic;

import net.minecraft.util.math.Vec3d;

public class DistancePair {
    public Vec3d Origin;
    public Float Distance;

    public DistancePair(Vec3d origin, Float distance) {
        Origin = origin;
        Distance = distance;
    }
}