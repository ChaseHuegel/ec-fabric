package io.chasehuegel.ecfabric.loot;

import io.chasehuegel.ecfabric.EternalCraft;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;

public abstract class LootModifier {
    
    protected abstract String getPrefix();

    protected abstract String getSuffix();

    public abstract boolean canApply(ItemStack stack);

    public abstract ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier);

    public boolean tryApply(ItemStack stack, EquipmentSlot slot, float strength, int tier)
    {
        if (!canApply(stack))
            return false;
        
        apply(stack, slot, strength, tier);
        return true;
    }

    public ItemStack apply(ItemStack stack, EquipmentSlot slot, float strength, int tier)
    {
        return modify(stack, slot, strength, tier);
    }

    public void applyName(ItemStack stack, int tier)
    {
        MutableText name = new LiteralText("").setStyle(Style.EMPTY);
        String prefix = getPrefix();
        String suffix = getSuffix();
        boolean usePrefix = !prefix.isEmpty() && (suffix.isEmpty() || EternalCraft.Random.nextBoolean());
        
        if (usePrefix)
            name.append(prefix + " ");

        name.append(new TranslatableText(stack.getTranslationKey()));

        if (!usePrefix && !suffix.isEmpty())
            name.append(" " + suffix);

        switch (tier) {
            case 1:
                name.formatted(LootRarity.COMMON.formatting);
                break;
            case 2:
                name.formatted(LootRarity.UNCOMMON.formatting);
                break;
            case 3:
                name.formatted(LootRarity.RARE.formatting);
                break;
            case 4:
                name.formatted(LootRarity.VERY_RARE.formatting);
                break;
            case 5:
                name.formatted(LootRarity.EPIC.formatting);
                break;
            case 6:
                name.formatted(LootRarity.LEGENDARY.formatting);
                break;
        }
        
        stack.setCustomName(name);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(this.getClass());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
