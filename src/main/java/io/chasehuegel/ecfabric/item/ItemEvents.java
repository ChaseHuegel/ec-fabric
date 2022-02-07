package io.chasehuegel.ecfabric.item;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class ItemEvents implements EndTick {
    public static void Initialize() {
        ItemEvents eventHandler = new ItemEvents();
        
        ServerTickEvents.END_SERVER_TICK.register(eventHandler);
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (player.getAir() < player.getMaxAir() && player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.COPPER_REBREATHER) {
                player.setAir(player.getAir()+1);
            }

            if (player.isFreezing() &&
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.FUR_HELMET ||
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == CustomItems.FUR_CHESTPLATE ||
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == CustomItems.FUR_LEGGINGS ||
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == CustomItems.FUR_BOOTS ||
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.SNOW_HELMET ||
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == CustomItems.SNOW_CHESTPLATE) {
                    player.setFrozenTicks(0);
            }
        }
    }
}
