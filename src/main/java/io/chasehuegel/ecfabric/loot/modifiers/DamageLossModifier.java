package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class DamageLossModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(DamageLossModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Dull",
        "Bent",
        "Pitted",
        "Flimsy",
        "Battle-scarred", "Frail",
        "Battered",
        "Used",
        "War-worn",
        "Puny",
        "Small",
        "Gnomish",
        "Used",
    };

    private String[] SuffixAliases = new String[] {
        "of the Weak",
        "of the Puny",
        "of the Frail",
        "of the Small",
        "of the Gnome",
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
        float value = -0.6f + (strength * tier * 0.1f);
        if (value > 0f)
            value = 0f;
        
            stack.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.damageloss", value, Operation.MULTIPLY_BASE), slot);
        
        return stack;
    }

}
