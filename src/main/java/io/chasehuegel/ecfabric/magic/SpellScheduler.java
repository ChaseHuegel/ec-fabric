package io.chasehuegel.ecfabric.magic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.util.math.Vec3d;

public class SpellScheduler {
    private static SpellScheduler instance;

    private Map<Entity, Integer> entityLifetimeMap = new HashMap<Entity, Integer>();
    private Map<Entity, DistancePair> entityDistanceMap = new HashMap<Entity, DistancePair>();
    private List<Entity> entityMovementList = new ArrayList<Entity>();
    private Map<Entity, Vec3d> entityVelocityMap = new HashMap<Entity, Vec3d>();

    public static SpellScheduler Instance() {
        return instance == null ? instance = new SpellScheduler() : instance;
    }

    public static void RemoveEntityAfterLifetime(Entity entity, int lifetime) {
        Instance().entityLifetimeMap.put(entity, lifetime);
    }

    public static void RemoveEntityAfterDistance(Entity entity, float distance) {
        Instance().entityDistanceMap.put(entity, new DistancePair(entity.getPos(), distance));
    }
    
    public static void RemoveEntityAfterMovement(Entity entity) {
        Instance().entityMovementList.add(entity);
    }
    
    public static void SetEntityVelocity(Entity entity, Vec3d velocity) {
        Instance().entityVelocityMap.put(entity, velocity);
    }

    public static void Tick() {
        List<Entity> entitiesToRemove = new ArrayList<Entity>();

        //  Lifetime removal
        for (Entry<Entity, Integer> pair : Instance().entityLifetimeMap.entrySet()) {
            int remainingLifetime = pair.getValue();
            Entity entity = pair.getKey();

            if (entity == null || entity.isRemoved()) {
                entitiesToRemove.add(entity);
                continue;
            }

            if (remainingLifetime <= 0) {
                entity.remove(RemovalReason.DISCARDED);
                entitiesToRemove.add(entity);
            } else {
                pair.setValue(remainingLifetime-1);
            }
        }
        for (Entity entity : entitiesToRemove) {
            Instance().entityLifetimeMap.remove(entity);
        } entitiesToRemove.clear();

        //  Distance removal
        for (Entry<Entity, DistancePair> pair : Instance().entityDistanceMap.entrySet()) {
            DistancePair distancePair = pair.getValue();
            Entity entity = pair.getKey();
            
            if (entity == null || entity.isRemoved()) {
                entitiesToRemove.add(entity);
                continue;
            }

            if (entity.getPos().distanceTo(distancePair.Origin) > distancePair.Distance) {
                entity.remove(RemovalReason.DISCARDED);
                entitiesToRemove.add(entity);
            }
        }
        for (Entity entity : entitiesToRemove) {
            Instance().entityDistanceMap.remove(entity);
        } entitiesToRemove.clear();

        //  Movement removal
        for (Entity entity : Instance().entityMovementList) {
            if (entity == null || entity.isRemoved()) {
                entitiesToRemove.add(entity);
                continue;
            }

            if (entity.getVelocity().length() <= 1d) {
                entity.remove(RemovalReason.DISCARDED);
                entitiesToRemove.add(entity);
            }
        }
        Instance().entityMovementList.removeAll(entitiesToRemove);

        //  Constant velocity
        for (Entry<Entity, Vec3d> pair : Instance().entityVelocityMap.entrySet()) {
            Entity entity = pair.getKey();
            
            if (entity == null || entity.isRemoved()) {
                entitiesToRemove.add(entity);
            } else {
                Vec3d velocity = pair.getValue();
                entity.setVelocity(velocity);
            }
        }
        for (Entity entity : entitiesToRemove) {
            Instance().entityVelocityMap.remove(entity);
        } entitiesToRemove.clear();

    }

}