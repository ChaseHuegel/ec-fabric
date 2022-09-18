package io.chasehuegel.ecfabric.loot.modifiers;

import java.util.UUID;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.loot.LootModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;

public class DamageBonusModifier extends LootModifier {

    public static final UUID MODIFIER_ID = EternalCraft.generateUuid(DamageBonusModifier.class.getSimpleName());

    private String[] PrefixAliases = new String[] {
        "Meteoric",
        "Grinding",
        "Jagged",
        "Deadly",
        "Vicious",
        "Brutal",
        "Massive",
        "Savage",
        "Merciless",
        "Ferocious",
        "Cruel",
        "Sharp",
        "Fine",
        "Masterwork",
        "Artisan",
    };

    private String[] SuffixAliases = new String[] {
        "of the Warrior",
        "of the Knight",
        "of the Lord",
        "of the King",
        "of the Master",
        "of the Grandmaster",
        "of Savagry",
        "of Ferocity",
        "of Cruelty",
        "of Edges",
        "of Craftsmanship",
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
        return stack.getItem() instanceof ToolItem || stack.getItem() instanceof ArmorItem || stack.getItem() instanceof TridentItem;
    }

    public ItemStack modify(ItemStack stack, EquipmentSlot slot, float strength, int tier) {
        float value = strength * tier / 3;
        
        stack.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(MODIFIER_ID, "ecfabric.damagebonus", value, Operation.ADDITION), slot);
        
        return stack;
    }

}
