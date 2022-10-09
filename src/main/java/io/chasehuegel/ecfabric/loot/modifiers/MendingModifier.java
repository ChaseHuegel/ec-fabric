package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class MendingModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(MendingModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Immortal",
        "Eternal",
        "Replenishing",
        "Mad",
        "Soulforged",
    };

    private String[] SuffixAliases = new String[] {
        "of Restoration",
        "of Self-Repair",
        "of Eternity",
        "of Souls",
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
        return stack.getItem() instanceof ArmorItem || stack.getItem() instanceof RangedWeaponItem || stack.getItem() instanceof ShieldItem || stack.getItem() instanceof ToolItem || stack.getItem() instanceof TridentItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        stack.addEnchantment(Enchantments.MENDING, 1);
        return stack;
    }

}
