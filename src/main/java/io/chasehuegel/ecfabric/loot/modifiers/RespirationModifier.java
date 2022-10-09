package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class RespirationModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(RespirationModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Selkie's",
        "Mer",
        "Mermaid's",
        "Siren's",
        "Oceanic",
        "Diver's",
        "Airy",
    };

    private String[] SuffixAliases = new String[] {
        "of the Selkie",
        "of the Mer",
        "of the Mermaid",
        "of the Nymph",
        "of the Deep",
        "of Respiration",
        "of Breath",
        "of Air",
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
        return stack.getItem() instanceof ArmorItem && ((ArmorItem)stack.getItem()).getSlotType() == EquipmentSlot.HEAD;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        int value = (int) (strength * tier / 2);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(Enchantments.RESPIRATION, value);
        return stack;
    }

}
