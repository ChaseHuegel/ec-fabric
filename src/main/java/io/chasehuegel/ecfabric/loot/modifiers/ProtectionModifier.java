package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class ProtectionModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(ProtectionModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Protecting",
        "Shielding",
        "Shielded",
        "Sturdy",
        "Glorious",
        "Blessed",
        "Saintly",
        "Holy",
        "Faithful",
        "Shimmering",
        "Prismatic",
        "Deflecting",
        "Evasive",
    };

    private String[] SuffixAliases = new String[] {
        "of Blessings",
        "of the Faithful",
        "of Faith",
        "of Glory",
        "of Protection",
        "of Shielding",
        "of the Guardian",
        "of Deflection",
        "of Dodging",
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
        int value = (int) (strength * tier);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(Enchantments.PROTECTION, value);
        return stack;
    }

}
