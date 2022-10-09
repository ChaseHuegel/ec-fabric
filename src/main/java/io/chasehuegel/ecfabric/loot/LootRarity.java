package io.chasehuegel.ecfabric.loot;

import net.minecraft.util.Formatting;

public enum LootRarity {
   COMMON(Formatting.WHITE),
   UNCOMMON(Formatting.GREEN),
   RARE(Formatting.YELLOW),
   VERY_RARE(Formatting.AQUA),
   EPIC(Formatting.LIGHT_PURPLE),
   LEGENDARY(Formatting.GOLD);

   public final Formatting formatting;

   private LootRarity(Formatting formatting) {
      this.formatting = formatting;
   }
}