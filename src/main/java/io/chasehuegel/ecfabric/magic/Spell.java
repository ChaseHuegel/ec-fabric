package io.chasehuegel.ecfabric.magic;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleType;
import net.minecraft.sound.SoundEvent;

public class Spell {
    public String Name;
    public int MaxLevel;
    public Item Component;
    
    public SpellType Type;

    public EntityType<?> ProjectileEntity;
    public float ProjectileVelocity;

    public EntityType<?> SummonEntity;
    public int SummonDuration;

    public boolean DamageEnabled;
    public TargetMode DamageTarget;
    public float DamageAmount;
    public float DamageScale;

    public float GetDamage(int level) {
        return DamageAmount * (1f + (Math.min(level, MaxLevel) - 1) * DamageScale);
    }

    public boolean HealEnabled;
    public TargetMode HealTarget;
    public float HealAmount;
    public float HealScale;

    public float GetHeal(int level) {
        return HealAmount * (1f + (Math.min(level, MaxLevel) - 1) * HealScale);
    }

    public boolean EffectEnabled;
    public TargetMode EffectTarget;
    public StatusEffect Effect;
    public int EffectDuration;
    public int EffectAmplifier;
    public float EffectScale;
    public boolean ShowEffectParticles;
    public boolean IsEffectVisible;

    public int GetEffectDuration(int level) {
        return (int)(EffectDuration * (1f + (Math.min(level, MaxLevel) - 1) * EffectScale));
    }

    public int GetEffectAmplifier(int level) {
        return (int)(EffectAmplifier * (1f + (Math.min(level, MaxLevel) - 1) * EffectScale));
    }

    public StatusEffectInstance GetStatusEffectInstance() {
        return new StatusEffectInstance(Effect, EffectDuration, EffectAmplifier, ShowEffectParticles, IsEffectVisible);
    }

    public SoundEvent CastSound;
    public ParticleType<?> CastParticle;
    public int CastParticleCount;

    public SoundEvent HitSound;
    public ParticleType<?> HitParticle;
    public int HitParticleCount;

}
