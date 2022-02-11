package io.chasehuegel.ecfabric.item;

import io.chasehuegel.ecfabric.magic.SpellScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartTick;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class ItemEvents implements EndTick, StartTick {
    private ItemEvents() {}

    public static void Initialize() {
        ItemEvents eventHandler = new ItemEvents();        
        ServerTickEvents.END_SERVER_TICK.register(eventHandler);
        ServerTickEvents.START_SERVER_TICK.register(eventHandler);
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (player.getAir() < player.getMaxAir() && player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.COPPER_REBREATHER) {
                player.setAir(player.getAir()+1);
            }

            if (player.isFreezing() &&
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.THICK_FUR_HELMET ||
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == CustomItems.THICK_FUR_CHESTPLATE ||
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == CustomItems.THICK_FUR_LEGGINGS ||
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == CustomItems.THICK_FUR_BOOTS ||
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == CustomItems.SNOW_HELMET ||
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == CustomItems.SNOW_CHESTPLATE) {
                    player.setFrozenTicks(0);
            }
        }
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        SpellScheduler.Tick();
    }
}
