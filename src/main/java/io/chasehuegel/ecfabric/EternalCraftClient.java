package io.chasehuegel.ecfabric;

import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class EternalCraftClient implements ClientModInitializer {
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

        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.FINE_GLASS, RenderLayer.getCutout());
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
