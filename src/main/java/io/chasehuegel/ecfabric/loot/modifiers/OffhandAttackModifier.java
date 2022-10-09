package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class OffhandAttackModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(OffhandAttackModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Bashing",
        "Heavy",
        "Mighty",
        "Powerful",
        "Slamming",
        "Smacking",
        "Bull's",
        "Dueling",
        "Brandishing",
        "Vanquishing",
    };

    private String[] SuffixAliases = new String[] {
        "of Bashing",
        "of Smacking",
        "of the Bull",
        "of the Duelist",
        "of Dual Wielding",
        "of the Soldier",
        "of the Warrior",
        "of the Knight",
        "of the Hero",
        "of the Fighter",
        "of the Rogue",
        "of the Assassin",
        "of the Vanquisher",
        "of the Master",
        "of the Sensei",
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
        return !(stack.getItem() instanceof ArmorItem);
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {

        if (EternalCraft.Random.nextBoolean()) {
            float value = strength * tier / 3;

            stack.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(MODIFIER_ID, "ecfabric.offhandattack", value, Operation.ADDITION),
                EquipmentSlot.OFFHAND);
                
        } else {
            float value = strength * tier * 0.05f;

            stack.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(MODIFIER_ID, "ecfabric.offhandattack", value, Operation.MULTIPLY_BASE),
                EquipmentSlot.OFFHAND);
        }
        return stack;
    }

}
