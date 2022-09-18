package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class AttackSpeedBonusModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(AttackSpeedBonusModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Readied",
        "Hasty",
        "Quick",
        "Brilliant",
        "Mithril",
    };

    private String[] SuffixAliases = new String[] {
        "of Readiness",
        "of Alacrity",
        "of Quickness",
        "of Elves",
        "of Haste",
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
        float value = strength * tier * 0.05f;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.attackspeedbonus", value, Operation.MULTIPLY_BASE), slot);
        
        return stack;
    }

}
