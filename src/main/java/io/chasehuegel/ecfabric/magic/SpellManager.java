package io.chasehuegel.ecfabric.magic;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.chasehuegel.ecfabric.EternalCraft;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellManager implements SimpleSynchronousResourceReloadListener {
    private static Map<Item, Spell> spells;

    public static Spell TryGetFromComponent(Item component) {
        return spells.get(component);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(EternalCraft.Namespace, "spells");
    }

    @Override
    public void reload(ResourceManager manager) {	 
        spells = new HashMap<Item, Spell>();

        Collection<Identifier> resourceIds = manager.findResources("spells", path -> path.endsWith(".json"));
        
        if (resourceIds.size() > 0) {
            EternalCraft.LOGGER.info("Loading spells...");

            for(Identifier id : resourceIds) {
                try(InputStream stream = manager.getResource(id).getInputStream()) {
                    JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();

                    Spell spell = new Spell();
                    spell.Name = json.get("Name").getAsString();
                    spell.MaxLevel = json.get("MaxLevel").getAsInt();
                    spell.Component = Registry.ITEM.get(Identifier.tryParse(json.get("Component").getAsString().toLowerCase()));
                    spell.Type = SpellType.valueOf(json.get("Type").getAsString().toUpperCase());
                    
                    var projectile = json.getAsJsonObject("Projectile");
                    if (projectile != null) {
                        spell.ProjectileEntity = Registry.ENTITY_TYPE.get(Identifier.tryParse(projectile.get("Type").getAsString().toLowerCase()));
                        spell.ProjectileVelocity = projectile.get("Velocity").getAsFloat();
                    }
                    
                    var summon = json.getAsJsonObject("Summon");
                    if (summon != null) {
                        spell.SummonEntity = Registry.ENTITY_TYPE.get(Identifier.tryParse(summon.get("Type").getAsString().toLowerCase()));
                        spell.SummonDuration = summon.get("Duration").getAsInt();
                    }
                    
                    var damage = json.getAsJsonObject("Damage");
                    if (damage != null) {
                        spell.DamageEnabled = damage.get("Enabled").getAsBoolean();
                        spell.DamageTarget = TargetMode.valueOf(damage.get("Target").getAsString().toUpperCase());
                        spell.DamageAmount = damage.get("Value").getAsFloat();
                        spell.DamageScale = damage.get("Scale").getAsFloat();
                    } else {
                        spell.DamageEnabled = false;
                    }
                    
                    var heal = json.getAsJsonObject("Heal");
                    if (heal != null) {
                        spell.HealEnabled = heal.get("Enabled").getAsBoolean();
                        spell.HealTarget = TargetMode.valueOf(heal.get("Target").getAsString().toUpperCase());
                        spell.HealAmount = heal.get("Value").getAsFloat();
                        spell.HealScale = heal.get("Scale").getAsFloat();
                    } else {
                        spell.HealEnabled = false;
                    }
                    
                    var effect = json.getAsJsonObject("Effect");
                    if (effect != null) {
                        spell.EffectEnabled = effect.get("Enabled").getAsBoolean();
                        spell.EffectTarget = TargetMode.valueOf(effect.get("Target").getAsString().toUpperCase());
                        spell.Effect = Registry.STATUS_EFFECT.get(Identifier.tryParse(effect.get("Type").getAsString().toLowerCase()));
                        spell.EffectDuration = effect.get("Duration").getAsInt();
                        spell.EffectAmplifier = effect.get("Amplifier").getAsInt();
                        spell.EffectScale = effect.get("Scale").getAsFloat();
                        spell.ShowEffectParticles = effect.get("Particles").getAsBoolean();
                        spell.IsEffectVisible = effect.get("Visible").getAsBoolean();
                    } else {
                        spell.EffectEnabled = false;
                    }
                    
                    spell.CastSound = Registry.SOUND_EVENT.get(Identifier.tryParse(json.getAsJsonObject("OnCast").get("Sound").getAsString().toLowerCase()));
                    spell.CastParticle = (ParticleEffect) Registry.PARTICLE_TYPE.get(Identifier.tryParse(json.getAsJsonObject("OnCast").get("Particle").getAsString().toLowerCase()));
                    spell.CastParticleCount = json.getAsJsonObject("OnCast").get("ParticleCount").getAsInt();
                    
                    spell.HitSound = Registry.SOUND_EVENT.get(Identifier.tryParse(json.getAsJsonObject("OnHit").get("Sound").getAsString().toLowerCase()));
                    spell.HitParticle = (ParticleEffect) Registry.PARTICLE_TYPE.get(Identifier.tryParse(json.getAsJsonObject("OnHit").get("Particle").getAsString().toLowerCase()));
                    spell.HitParticleCount = json.getAsJsonObject("OnHit").get("ParticleCount").getAsInt();
                    
                    spells.put(spell.Component, spell);
                    EternalCraft.LOGGER.info("- " + spell.Name);
                } catch(Exception e) {
                    EternalCraft.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
                }
            }
        }
    }
    
}
