package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class SlowModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(SlowModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Heavy",
        "Hefty",
        "Weighted",
        "Bulky",
        "Oversized",
        "Ogre-sized",
        "Sluggish",
        "Oozing",
        "Dwarven",
        "Heavy-handed",
        "Heavy-footed",
    };

    private String[] SuffixAliases = new String[] {
        "of the Ogre",
        "of the Snail",
        "of the Slug",
        "of the Lead-Foot",
        "of Dwarves",
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
        return true;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        float value = -0.15f + (strength * tier * 0.025f);
        if (value > 0f)
            value = 0f;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.slow", value, Operation.MULTIPLY_BASE), slot);
        
        return stack;
    }

}
