package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ToolItem;

public class ArmorBonusModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(ArmorBonusModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Guarded",
        "Strong",
        "Reinforced",
        "Improved",
        "Fortified",
        "Artisan",
        "Masterwork",
        "Meteoric",
        "Fine",
    };

    @Override
    protected String getPrefix() {
        return PrefixAliases[EternalCraft.Random.nextInt(PrefixAliases.length)];
    }

    @Override
    protected String getSuffix() {
        return "";
    }

    public boolean canApply(ItemStack stack) {
        return !(stack.getItem() instanceof RangedWeaponItem) && !(stack.getItem() instanceof ToolItem);
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        float value = strength * tier / 3;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.armorbonus", value, Operation.ADDITION), slot);
        
        return stack;
    }

}
