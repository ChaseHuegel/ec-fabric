package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class MagicModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(MagicModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Mystical",
        "Ancient",
        "Elder",
        "Old",
        "Runic",
        "Elvish",
        "Forgotten",
        "Grave",
        "Enchanted",
        "Wondrous",
        "Glyphic",
        "Weird",
        "Magecrafted",
        "Orated",
        "Sagely",
        "Mage's",
        "Glittering",
        "Gleaming",
        "Strange",
        "Storied",
        "Wise",
        "Magical",
        "Ancestral",
    };

    private String[] SuffixAliases = new String[] {
        "of Old",
        "of Elder Days",
        "of Antiquity",
        "of the Elves",
        "of the Realm",
        "of Lost Realms",
        "of Incantations",
        "of Glyphs",
        "of Runes",
        "of Myth",
        "of the Sagas",
        "of the Accursed",
        "of the Bards",
        "of Stories",
        "of Legend",
        "of Mysticism",
        "of Shamanism",
        "of Sorcerery",
        "of Celeste",
        "of Wisdom",
        "of the Wise",
        "of Enchantment",
        "of Ages",
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
        return stack.getItem() instanceof ArmorItem || stack.getItem() instanceof ToolItem || stack.getItem() instanceof RangedWeaponItem || stack.getItem() instanceof ShieldItem || stack.getItem() instanceof TridentItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        int value = (int) (strength * tier * 5);
        value = value == 0 ? 1 : value;
        EnchantmentHelper.enchant(EternalCraft.Random, stack, value, true);
        return stack;
    }

}
