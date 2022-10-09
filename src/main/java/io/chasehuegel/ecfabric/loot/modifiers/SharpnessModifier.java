package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class SharpnessModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(SharpnessModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Corrosive",
        "Victorious",
        "Devious",
        "Berserker's",
        "Resonant",
        "Raging",
        "Feral",
        "Hexed",
        "Butcher's",
        "Eviscerating",
        "Vile",
        "Amplifying",
        "Vengeful",
        "Razor",
        "Cutting",
        "Slicing",
    };

    private String[] SuffixAliases = new String[] {
        "of Strength",
        "of Might",
        "of the Giant",
        "of Maiming",
        "of Gore",
        "of Carnage",
        "of Slaughter",
        "of Butchery",
        "of Evisceration",
        "of Vengeance",
        "of Sharpness",
        "of Slicing",
        "of Razors",
        "of Wrath",
        "of Agony",
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
        stack.addEnchantment(Enchantments.SHARPNESS, value);
        return stack;
    }

}
