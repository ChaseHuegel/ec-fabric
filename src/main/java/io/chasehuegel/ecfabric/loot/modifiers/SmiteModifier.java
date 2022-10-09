package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class SmiteModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(SmiteModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Blessed",
        "Lunar",
        "Arcadian",
        "Unearthly",
        "Astral",
        "Celestial",
        "Consecrated",
        "Pure",
        "Sacred",
        "Hallowed",
        "Divine",
        "Shining",
        "Shimmering",
        "Redeeming",
        "Holy",
        "Witch-Hunter",
        "Mageslayer",
        "Slayer's",
        "Priest's",
        "Innocent",
        "Fanatic",
        "Radiant",
        "Zealous",
        "Zealot's",
        "Banishing",
    };

    private String[] SuffixAliases = new String[] {
        "of Blessings",
        "of the Sun",
        "of the Moon",
        "of Redemption",
        "of Faith",
        "of Slaying",
        "of the Angel",
        "of the Fanatic",
        "of the Scrolls",
        "of Light",
        "of Radiance",
        "of Zeal",
        "of Banishing",
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
        return stack.getItem() instanceof ToolItem || stack.getItem() instanceof TridentItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        int value = (int) (strength * tier);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(Enchantments.SMITE, value);
        return stack;
    }

}
