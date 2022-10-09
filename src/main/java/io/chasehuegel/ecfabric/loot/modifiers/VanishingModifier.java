package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class VanishingModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(VanishingModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Vanishing",
        "Forgetful",
        "Cursed",
        "Spelled",
        "Dreaming",
        "Sorrowful",
    };

    private String[] SuffixAliases = new String[] {
        "of Vanishing",
        "of Forgetfulness",
        "of the Hag",
        "of Woe",
        "of Dreams",
        "of Sorrow",
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
        stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        return stack;
    }

}
