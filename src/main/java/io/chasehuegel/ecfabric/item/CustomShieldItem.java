package io.chasehuegel.ecfabric.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

public class CustomShieldItem extends FabricShieldItem {
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public CustomShieldItem(Settings settings, int cooldownTicks, int enchantability, float armor, float toughness, float knockbackResistance, Item... repairItems) {
        super(settings, cooldownTicks, enchantability, repairItems);

        attributeModifiers = ArrayListMultimap.create();

        if (armor > 0f) { 
            attributeModifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(String.format("%s:armor", EternalCraft.Namespace), armor, EntityAttributeModifier.Operation.ADDITION));
        }

        if (toughness > 0f) { 
            attributeModifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(String.format("%s:toughness", EternalCraft.Namespace), toughness, EntityAttributeModifier.Operation.ADDITION));
        }

        if (knockbackResistance > 0f) { 
            attributeModifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(String.format("%s:knockbackResistance", EternalCraft.Namespace), knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot != EquipmentSlot.OFFHAND) {
            return ImmutableMultimap.of();
        }

        return attributeModifiers;
    }
}