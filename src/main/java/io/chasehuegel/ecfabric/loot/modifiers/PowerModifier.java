package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;

public class PowerModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(PowerModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Hawkeye",
        "Serpent",
        "Fletcher's",
        "Hunter's",
        "Cunning",
        "Sounding",
        "Screaming",
        "Howling",
        "Wailing",
        "Precise",
        "Masterwork",
        "Fine",
        "Artisan",
        "Piercing",
        "Archer's",
        "Puncturing",
        "Perfect",
        "Biting",
        "Stinging",
    };

    private String[] SuffixAliases = new String[] {
        "of the Eagle-eye",
        "of Snakes",
        "of the Serpent",
        "of the Hunter",
        "of Dexterity",
        "of Skill",
        "of Accuracy",
        "of Precision",
        "of Piercing",
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
        return stack.getItem() instanceof RangedWeaponItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        int value = (int) (strength * tier);
        value = value == 0 ? 1 : value;
        stack.addEnchantment(Enchantments.POWER, value);
        return stack;
    }

}
