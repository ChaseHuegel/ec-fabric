package io.chasehuegel.ecfabric.item;

import java.util.function.Supplier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

public enum CustomArmorMaterials implements ArmorMaterial
{
    ARMORER("armorer", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    BUTCHER("butcher", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    DESERT("desert", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    FISHERMAN("fisherman", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    JUNGLE("jungle", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    MASON("mason", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    LEATHERWORKER("leatherworker", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    LIBRARIAN("librarian", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    PLAINS("plains", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SAVANNA("savanna", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SHEPHERD("shepherd", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SNOW("snow", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SWAMP("swamp", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    TAIGA("taiga", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SACK_MASK("sack_mask", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    SABITO_MASK("sabito_mask", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    BANDANA("bandana", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    ATLANTIS("atlantis", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    CROWN("crown", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    GRAY_WOLF("gray_wolf", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    THICK_FUR("thick_fur", 5, new int[]{1, 2, 3, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    CLERIC("cleric", 5, new int[]{1, 1, 2, 1}, 35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    ANGEL("angel", 5, new int[]{1, 1, 2, 1}, 35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    AZALEA("azalea", 5, new int[]{1, 1, 2, 1}, 35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    WIZARD("wizard", 5, new int[]{1, 1, 2, 1}, 35, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.THREAD)),
    PILLAGER("pillager", 15, new int[]{4, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    PIGLIN("piglin", 15, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 4.0f, 0.0f, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    BLAZE("blaze", 15, new int[]{3, 6, 8, 3}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.25f, () -> Ingredient.ofItems(Items.BLAZE_ROD)),
    PRISMARINE("prismarine", 15, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, 0f, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD)),
    COPPER_REBREATHER("copper_rebreather", 15, new int[]{0, 0, 0, 0}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    // LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.LEATHER)),
    BONE("bone", 10, new int[]{1, 3, 4, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.1f, () -> Ingredient.ofItems(Items.BONE)),
    // CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    COPPER("copper", 13, new int[]{2, 4, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    // IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    EASTERN("eastern", 15, new int[]{3, 5, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.STEEL_INGOT)),
    STEEL("steel", 20, new int[]{3, 5, 7, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> Ingredient.ofItems(CustomItems.STEEL_INGOT)),
    // GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    AMETHYST("amethyst", 12, new int[]{2, 3, 5, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0f, 0.0f, () -> Ingredient.ofItems(Items.LEATHER)),
    // DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, () -> Ingredient.ofItems(Items.DIAMOND)),
    PLATINUM("platinum", 25, new int[]{4, 7, 9, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.DIAMOND));
    // NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));
    // TURTLE("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.SCUTE)),

    private static final int[] BASE_DURABILITY;
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;

    private CustomArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<Ingredient>(repairIngredientSupplier);
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    static {
        BASE_DURABILITY = new int[]{13, 15, 16, 11};
    }
}