package io.chasehuegel.ecfabric;

import io.chasehuegel.ecfabric.item.CustomItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class EternalCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(CustomItems.AMETHYST_STAFF, new Identifier("charge"), (itemStack, clientWorld, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });
         
        FabricModelPredicateProviderRegistry.register(CustomItems.AMETHYST_STAFF, new Identifier("casting"), (itemStack, clientWorld, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}
