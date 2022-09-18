package io.chasehuegel.ecfabric.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import io.chasehuegel.ecfabric.EternalCraft;
import io.chasehuegel.ecfabric.item.CustomStaffItem;
import io.chasehuegel.ecfabric.loot.modifiers.LootModifiers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class RpgItemFunction implements LootFunction {

   private static List<Item> ArmorItems;
   private static List<Item> MeleeWeaponItems;
   private static List<Item> MagicItems;
   private static List<Item> RangedWeaponItems;
   private static List<Item> ShieldItems;
   private static List<Item> ToolItems;
   private static List<Item> TreasureItems;

   private int addModifiers(ItemStack stack, EquipmentSlot slot, float luck) {
      float rarity = EternalCraft.Random.nextFloat();
      int tier = rarityToTier(rarity);

      // Collect which modifiers we can use on this item
      List<LootModifier> validModifiers = new ArrayList<LootModifier>();
      for (LootModifier modifier : LootModifiers.MODIFIERS)
         if (modifier.canApply(stack))
            validModifiers.add(modifier);
      
      // Collect the modifiers we will be applying, not allowing duplicates
      List<LootModifier> modifiers = new ArrayList<LootModifier>();
      for (int i = 0; i < tier; i++) {
         LootModifier modifier = validModifiers.get(EternalCraft.Random.nextInt(validModifiers.size()));
         if (modifier.canApply(stack) && !modifiers.contains(modifier))
            modifiers.add(modifier);
      }

      // Apply modifiers, scaling strength based on the wanted vs actual # of modifiers
      // so if we get less than the tier should have then modifiers are more powerful
      float strength = rarity * (tier / modifiers.size());
      for (LootModifier modifier : modifiers)
         modifier.apply(stack, slot, strength, tier);
      
      applyNameFromModifiers(stack, tier, modifiers);

      return tier;
   }

   private void applyNameFromModifiers(ItemStack stack, int tier, List<LootModifier> modifiers) {
      List<String> prefixes = new ArrayList<String>();
      List<String> suffixes = new ArrayList<String>();
      for (LootModifier modifier : modifiers)
      {
         String prefix = modifier.getPrefix();
         String suffix = modifier.getSuffix();
         boolean usePrefix = !prefix.isEmpty() && (suffix.isEmpty() || EternalCraft.Random.nextBoolean());

         if (usePrefix)
            prefixes.add(prefix);

         if (!usePrefix && !suffix.isEmpty())
            suffixes.add(suffix);
      }
      
      MutableText name = new LiteralText("").setStyle(Style.EMPTY);

      for (String prefix : prefixes)
         name.append(prefix + " ");

      name.append(new TranslatableText(stack.getTranslationKey()));

      if (suffixes.size() > 0)
         name.append(" " + suffixes.get(EternalCraft.Random.nextInt(suffixes.size())));

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

   private int rarityToTier(float rarity) {
      if (rarity <= 0.5f)
         return 1;
      if (rarity <= 0.7f)
         return 2;
      if (rarity <= 0.8f)
         return 3;
      if (rarity <= 0.92f)
         return 4;
      if (rarity <= 0.97f)
         return 5;
      if (rarity <= 1f)
         return 6;

      return 1;
   }

   @Override
   public ItemStack apply(ItemStack stack, LootContext context) {
      if (!context.hasParameter(LootContextParameters.LAST_DAMAGE_PLAYER))
         return stack;
      
      // Reroll a # of times based off luck
      float roll = EternalCraft.Random.nextFloat();
      for (int i = 0; i < context.getLuck(); i++) {
         float value = EternalCraft.Random.nextFloat();
         if (value < roll)
            roll = value;
      }

      int looting = 0;
      for (ItemStack itemInHand : context.get(LootContextParameters.KILLER_ENTITY).getItemsHand()) {
         looting = looting == 0 ? EnchantmentHelper.getLevel(Enchantments.LOOTING, itemInHand) : looting;     
      }

      LivingEntity victim = (LivingEntity) context.get(LootContextParameters.THIS_ENTITY);
      float dropChance = victim.getMaxHealth() * 0.005f + (looting * 0.01f);
      if (roll > dropChance)
         return stack;
      
      // Only loot mobs, unless we have a looting enchant
      if (!(victim instanceof MobEntity) && looting == 0) {
         return stack;
      }
      
      // Lazily collect lootable items from the registry
      if (ArmorItems == null) {
         ArmorItems = new ArrayList<Item>();
         ToolItems = new ArrayList<Item>();
         MeleeWeaponItems = new ArrayList<Item>();
         MagicItems = new ArrayList<Item>();
         RangedWeaponItems = new ArrayList<Item>();
         ShieldItems = new ArrayList<Item>();
         TreasureItems = new ArrayList<Item>();

         Registry.ITEM.forEach((item) -> {
            if (item.getRarity(item.getDefaultStack()) == Rarity.COMMON) {

               if (item instanceof ArmorItem)
                  ArmorItems.add(item);
               else if (item instanceof CustomStaffItem)
                  MagicItems.add(item);
               else if (item instanceof RangedWeaponItem)
                  RangedWeaponItems.add(item);
               else if (item instanceof ShieldItem)
                  ShieldItems.add(item);
               else if (item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem)
                  MeleeWeaponItems.add(item);
               else if (item instanceof ToolItem)
                  ToolItems.add(item);
               else if (!(item instanceof BlockItem))
                  TreasureItems.add(item);
            }
         });
      }
      
      if (EternalCraft.Random.nextFloat() < 0.8) {
         List<Item> lootList = ArmorItems;

         float tableRoll = EternalCraft.Random.nextFloat();
         if (tableRoll <= 0.05f)
            lootList = TreasureItems;
         else if (tableRoll <= 0.15f)
            lootList = MagicItems;
         else if (tableRoll <= 0.35f)
            lootList = RangedWeaponItems;
         else if (tableRoll <= 0.50f)
            lootList = ShieldItems;
         else if (tableRoll <= 0.75f)
            lootList = ArmorItems;
         else if (tableRoll <= 0.95f)
            lootList = MeleeWeaponItems;
         else if (tableRoll <= 1f)
            lootList = ToolItems;

         stack = lootList.get(EternalCraft.Random.nextInt(lootList.size())).getDefaultStack();
      }

      stack.setCount(1);

      for (EquipmentSlot slot : EquipmentSlot.values()) {
         for (Entry<EntityAttribute, EntityAttributeModifier> entry : stack.getItem().getAttributeModifiers(slot)
               .entries()) {
            stack.addAttributeModifier(entry.getKey(), entry.getValue(), slot);
         }
      }
      
      int tier = 0;
      if (stack.getItem() instanceof ArmorItem) {
         ArmorItem armor = (ArmorItem) stack.getItem();
         tier = addModifiers(stack, armor.getSlotType(), context.getLuck());
      } else if (stack.getItem() instanceof ShieldItem) {
         tier = addModifiers(stack, EquipmentSlot.OFFHAND, context.getLuck());
      } else if (stack.getItem() instanceof ToolItem) {
         tier = addModifiers(stack, EquipmentSlot.MAINHAND, context.getLuck());
      } else if (stack.getItem() instanceof RangedWeaponItem) {
         tier = addModifiers(stack, EquipmentSlot.MAINHAND, context.getLuck());
      } else if (stack.getItem() instanceof TridentItem) {
         tier = addModifiers(stack, EquipmentSlot.MAINHAND, context.getLuck());
      } else {
         tier = addModifiers(stack, EquipmentSlot.OFFHAND, context.getLuck());
      }

      if (stack.getItem().getMaxDamage() > 0)
         stack.setDamage(EternalCraft.Random.nextInt(stack.getItem().getMaxDamage()));

      if (tier >= 6) {
         PlayerEntity attacker = (PlayerEntity) context.get(LootContextParameters.DAMAGE_SOURCE).getAttacker();
         TranslatableText mutableText = new TranslatableText("");
         mutableText.append(attacker.getName());
         mutableText.append(Text.of(" found a legendary ["));
         mutableText.append(stack.getName());
         mutableText.append(Text.of("]!"));
         attacker.getServer().getPlayerManager().broadcast(mutableText, MessageType.CHAT, Util.NIL_UUID);
      }

      return stack;
   }

   @Override
   public LootFunctionType getType() {
      return null;
   }
}
