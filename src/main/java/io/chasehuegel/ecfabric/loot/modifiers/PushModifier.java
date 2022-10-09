package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class PushModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(PushModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Ox",
        "Ram",
        "Donkey",
        "Centaur",
        "Stunning",
        "Great",
        "Powerful",
        "Colossal",
        "Monstrous",
        "Absolute",
        "Stunning",
    };

    private String[] SuffixAliases = new String[] {
        "of the Ox",
        "of the Ram",
        "of the Donkey",
        "of the Centaur",
        "of the Giant",
        "of Colossus",
        "of the Bee",
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
        return stack.getItem() instanceof ToolItem || stack.getItem() instanceof RangedWeaponItem || stack.getItem() instanceof TridentItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        Enchantment enchantment = stack.getItem() instanceof RangedWeaponItem ? Enchantments.PUNCH : Enchantments.KNOCKBACK;
        int value = (int) (strength * tier / 2);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(enchantment, value);
        return stack;
    }

}
