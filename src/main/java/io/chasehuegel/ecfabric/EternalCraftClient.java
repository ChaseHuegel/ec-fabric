package io.chasehuegel.ecfabric;

import io.chasehuegel.ecfabric.block.CustomBlocks;
import io.chasehuegel.ecfabric.item.CustomItems;
import io.chasehuegel.ecfabric.magic.SpellManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
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
        FabricModelPredicateProviderRegistry.register(CustomItems.BURNING_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.BURNING_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.GOLDEN_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.GOLDEN_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.DIAMOND_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.DIAMOND_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.MYSTIC_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.MYSTIC_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        
        FabricModelPredicateProviderRegistry.register(CustomItems.BLOWGUN, new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.BLOWGUN, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        
        FabricModelPredicateProviderRegistry.register(CustomItems.SLING, new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.SLING, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.LONGBOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> chargePredicate(itemStack, clientWorld, livingEntity));
        FabricModelPredicateProviderRegistry.register(CustomItems.LONGBOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> chargingPredicate(itemStack, clientWorld, livingEntity));

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

        ClientPlayNetworking.registerGlobalReceiver(EternalCraft.SPELL_RELOAD_PACKET, (client, handler, buffer, sender) -> { SpellManager.OnSpellReload(); });
		ClientPlayNetworking.registerGlobalReceiver(EternalCraft.SPELL_DATA_PACKET, (client, handler, buffer, sender) -> { SpellManager.OnSpellRecieved(buffer); });
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
