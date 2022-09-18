package io.chasehuegel.ecfabric.loot.modifiers;

import io.chasehuegel.ecfabric.loot.LootModifier;

public class LootModifiers {
    public final static LootModifier[] MODIFIERS = new LootModifier[] {
        new DamageLossModifier(),
        new DamageBonusModifier(),
        new ArmorLossModifier(),
        new ArmorBonusModifier(),
        new SlowModifier(),
        new SpeedModifier(),
        new LuckModifier(),
        new ToughnessBonusModifier(),
        new MagicModifier(),
        new ProtectionModifier(),
        new PowerModifier(),
        new SmiteModifier(),
        new FireModifier(),
        new SharpnessModifier(),
        new HealthBonusModifier(),
        new PushModifier(),
        new AttackSpeedBonusModifier(),
        new ThornsModifier(),
        new MendingModifier(),
        new LootingModifier(),
        new RespirationModifier(),
        new BindingModifier(),
        new UnbreakingModifier(),
        new VanishingModifier(),
        new OffhandAttackModifier(),
   };
}