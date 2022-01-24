package io.chasehuegel.ecfabric.item;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

public class CustomTrinket extends TrinketItem {
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public CustomTrinket(Settings settings, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
        super(settings.maxCount(1));

        this.attributeModifiers = attributeModifiers;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        return attributeModifiers;
    }
}