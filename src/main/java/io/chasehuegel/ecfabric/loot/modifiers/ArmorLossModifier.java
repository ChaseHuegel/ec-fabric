package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class ArmorLossModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(ArmorLossModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Dented",
        "Bent",
        "Pitted",
        "Flimsy",
        "Scratched",
        "Battle-scarred",
        "Worn",
        "Weary",
        "Frail",
        "Battered",
        "War-worn",
        "Used",
    };

    private String[] SuffixAliases = new String[] {
        "of the Pummelled",
        "of the Beaten",
        "of the Battered",
        "of Adversity",
        "of Scars",
        "of Pity",
        "of Weakness",
        "of Losers",
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
        return stack.getItem() instanceof ArmorItem || stack.getItem() instanceof ShieldItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        float value = -strength * (6 - tier);
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.armorloss", value, Operation.ADDITION), slot);
        
        return stack;
    }

}
