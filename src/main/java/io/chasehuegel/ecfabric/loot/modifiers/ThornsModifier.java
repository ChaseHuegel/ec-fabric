package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class ThornsModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(ThornsModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Thorny",
        "Spiked",
        "Mirrorgleam",
        "Reflecting",
        "Wicked",
        "Warlord's",
    };

    private String[] SuffixAliases = new String[] {
        "of Thorns",
        "of Spikes",
        "of Razors",
        "of Reflection",
        "of Blood",
        "of Pain",
        "of Terror",
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
        return stack.getItem() instanceof ArmorItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        int value = (int) (strength * tier / 2);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(Enchantments.THORNS, value);
        return stack;
    }

}
