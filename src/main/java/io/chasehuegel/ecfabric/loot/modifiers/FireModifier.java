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

public class FireModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(FireModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Burning",
        "Fiery",
        "Smoldering",
        "Smoking",
        "Flaming",
        "Condensing",
        "Glowing",
        "Branded",
        "Branding",
        "Blazing",
        "Volcanic",
        "Sparking",
        "Fissure",
        "Searing",
        "Melting",
        "Molten",
    };

    private String[] SuffixAliases = new String[] {
        "of Fire",
        "of Branding",
        "of the Branded",
        "of Inceration",
        "of Flames",
        "of Melting",
        "of Searing",
        "of Burning",
        "of Blazes",
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
        int value = (int) (strength * tier / 2);
        value = value == 0 ? 1 : value;
        Enchantment enchantment = stack.getItem() instanceof RangedWeaponItem ? Enchantments.FLAME : Enchantments.FIRE_ASPECT;
        stack.addEnchantment(enchantment, value);
        return stack;
    }

}