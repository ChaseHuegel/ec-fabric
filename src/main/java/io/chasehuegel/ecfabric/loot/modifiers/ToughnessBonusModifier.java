package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class ToughnessBonusModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(ToughnessBonusModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Titanic",
        "Thick",
        "Vigilant",
        "Meaty",
        "Incredible",
        "Bold",
        "Prideful",
    };

    private String[] SuffixAliases = new String[] {
        "of Pride",
        "of Absolution",
        "of the Bold",
        "of Kings",
        "of the Lion",
        "of the Bear",
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
        float value = strength * tier / 3;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.toughnessbonus", value, Operation.ADDITION), slot);
        
        return stack;
    }

}
