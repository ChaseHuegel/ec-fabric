package io.chasehuegel.ecfabric.item.Trinkets;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class AmethystAmulet extends TrinketItem {
    public AmethystAmulet(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);

        // +10% movement speed
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, String.format("%s:movement_speed", EternalCraft.Namespace), 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        
        return modifiers;
    }
}