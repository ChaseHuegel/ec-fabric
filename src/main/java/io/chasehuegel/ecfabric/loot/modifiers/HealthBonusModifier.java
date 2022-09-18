package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class HealthBonusModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(HealthBonusModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Rugged",
        "Vigorous",
        "Hearty",
        "Healthy",
        "Tireless",
        "Ruby",
        "Leeching",
        "Everlasting",
        "Absorbing",
        "Warding",
        "Enduring",
    };

    private String[] SuffixAliases = new String[] {
        "of the Leech",
        "of Health",
        "of Life",
        "of Absorption",
        "of Warding",
        "of Negation",
        "of Endurance",
    };

    @Override
    protected String getPrefix() {
        return PrefixAliases[EternalCraft.Random.nextInt(PrefixAliases.length)];
    }

    @Override
    protected String getSuffix() {
        return SuffixAliases[EternalCraft.Random.nextInt(SuffixAliases.length)];
    }

    public boolean canApply(ItemStack stack) {
        return true;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        float value = strength * tier;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.healthbonus", value, Operation.ADDITION), slot);
        
        return stack;
    }

}
