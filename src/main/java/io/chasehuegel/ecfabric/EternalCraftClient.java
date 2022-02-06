package io.chasehuegel.ecfabric;

import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class EternalCraftClient implements ClientModInitializer {
    private static final Identifier PILLAGER_LOOT_TABLE_ID = EntityType.PILLAGER.getLootTableId();
    private static final Identifier PIGLIN_LOOT_TABLE_ID = EntityType.PIGLIN.getLootTableId();
    private static final Identifier BLAZE_LOOT_TABLE_ID = EntityType.BLAZE.getLootTableId();
    private static final Identifier DROWNED_LOOT_TABLE_ID = EntityType.DROWNED.getLootTableId();

    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(CustomItems.WOODEN_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.WOODEN_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.AMETHYST_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.AMETHYST_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.STEEL_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.STEEL_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.BONE_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.BONE_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.FIRE_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.FIRE_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.STAR_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.STAR_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.SEA_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.SEA_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.NETHERITE_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.NETHERITE_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.EMERALD_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.EMERALD_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.SOUL_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.SOUL_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.ENDER_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.ENDER_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.TIME_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.TIME_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.COPPER_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.COPPER_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        
        FabricModelPredicateProviderRegistry.register(CustomItems.BLOWGUN, new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.BLOWGUN, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));

        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.FINE_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.FROST, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.ICICLES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.HANGING_MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.BASKET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.FUNGUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.CLOVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.FLOWER_COVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.COPPER_VANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.LAMP, RenderLayer.getTranslucent());

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (PILLAGER_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder().build())
                        .withCondition(RandomChanceWithLootingLootCondition.builder(0.05f, 0.02f).build())
                        .with(ItemEntry.builder(CustomItems.PILLAGER_HELMET))
                        .with(ItemEntry.builder(CustomItems.PILLAGER_CHESTPLATE))
                        .with(ItemEntry.builder(CustomItems.PILLAGER_LEGGINGS))
                        .with(ItemEntry.builder(CustomItems.PILLAGER_BOOTS));
         
                table.pool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (PIGLIN_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder().build())
                        .withCondition(RandomChanceWithLootingLootCondition.builder(0.05f, 0.02f).build())
                        .with(ItemEntry.builder(CustomItems.PIGLIN_HELMET))
                        .with(ItemEntry.builder(CustomItems.PIGLIN_CHESTPLATE))
                        .with(ItemEntry.builder(CustomItems.PIGLIN_LEGGINGS))
                        .with(ItemEntry.builder(CustomItems.PIGLIN_BOOTS));
         
                table.pool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (BLAZE_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder().build())
                        .withCondition(RandomChanceWithLootingLootCondition.builder(0.05f, 0.02f).build())
                        .with(ItemEntry.builder(CustomItems.BLAZE_HELMET))
                        .with(ItemEntry.builder(CustomItems.BLAZE_CHESTPLATE))
                        .with(ItemEntry.builder(CustomItems.BLAZE_LEGGINGS))
                        .with(ItemEntry.builder(CustomItems.BLAZE_BOOTS));
         
                table.pool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (DROWNED_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder().build())
                        .withCondition(RandomChanceWithLootingLootCondition.builder(0.05f, 0.02f).build())
                        .with(ItemEntry.builder(CustomItems.PRISMARINE_HELMET))
                        .with(ItemEntry.builder(CustomItems.PRISMARINE_CHESTPLATE))
                        .with(ItemEntry.builder(CustomItems.PRISMARINE_LEGGINGS))
                        .with(ItemEntry.builder(CustomItems.PRISMARINE_BOOTS));
         
                table.pool(poolBuilder);
            }
        });
    }

    private float chargePredicate(ItemStack itemStack, ClientWorld clientWorld, LivingEntity livingEntity) {
        if (livingEntity == null) {
            return 0.0F;
        }
        return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
    }

    private float chargingPredicate(ItemStack itemStack, ClientWorld clientWorld, LivingEntity livingEntity) {
        if (livingEntity == null) {
            return 0.0F;
        }
        return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
    }
}
