package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class BindingModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(BindingModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Clinging",
        "Bound",
        "Binding",
        "Cursed",
        "Spelled",
    };

    private String[] SuffixAliases = new String[] {
        "of Binding",
        "of Clinging",
        "of the Hag",
        "of Sin",
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
        stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
        return stack;
    }

}
