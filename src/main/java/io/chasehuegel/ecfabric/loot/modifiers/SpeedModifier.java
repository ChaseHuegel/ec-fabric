package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

public class SpeedModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(SpeedModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Undersized",
        "Light",
        "Thief's",
        "Speedy",
        "Swift",
        "Accelerating",
        "Hermes",
        "Winged",
    };

    private String[] SuffixAliases = new String[] {
        "of Acceleration",
        "of Swiftness",
        "of Pacing",
        "of Hermes",
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
        float value = strength * tier * 0.025f;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.speed", value, Operation.MULTIPLY_BASE), slot);
        
        return stack;
    }

}
