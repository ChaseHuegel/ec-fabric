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
                    
                    spell.ProjectileEntity = Registry.ENTITY_TYPE.get(Identifier.tryParse(json.getAsJsonObject("Projectile").get("Type").getAsString().toLowerCase()));
                    spell.ProjectileVelocity = json.getAsJsonObject("Projectile").get("Velocity").getAsFloat();
                    
                    spell.SummonEntity = Registry.ENTITY_TYPE.get(Identifier.tryParse(json.getAsJsonObject("Summon").get("Type").getAsString().toLowerCase()));
                    spell.SummonDuration = json.getAsJsonObject("Summon").get("Duration").getAsInt();
                    
                    spell.DamageEnabled = json.getAsJsonObject("Damage").get("Enabled").getAsBoolean();
                    spell.DamageTarget = TargetMode.valueOf(json.getAsJsonObject("Damage").get("Target").getAsString().toUpperCase());
                    spell.DamageAmount = json.getAsJsonObject("Damage").get("Value").getAsFloat();
                    spell.DamageScale = json.getAsJsonObject("Damage").get("Scale").getAsFloat();
                    
                    spell.HealEnabled = json.getAsJsonObject("Heal").get("Enabled").getAsBoolean();
                    spell.HealTarget = TargetMode.valueOf(json.getAsJsonObject("Heal").get("Target").getAsString().toUpperCase());
                    spell.HealAmount = json.getAsJsonObject("Heal").get("Value").getAsFloat();
                    spell.HealScale = json.getAsJsonObject("Heal").get("Scale").getAsFloat();
                    
                    spell.EffectEnabled = json.getAsJsonObject("Effect").get("Enabled").getAsBoolean();
                    spell.EffectTarget = TargetMode.valueOf(json.getAsJsonObject("Effect").get("Target").getAsString().toUpperCase());
                    spell.Effect = Registry.STATUS_EFFECT.get(Identifier.tryParse(json.getAsJsonObject("Effect").get("Type").getAsString().toLowerCase()));
                    spell.EffectDuration = json.getAsJsonObject("Effect").get("Duration").getAsInt();
                    spell.EffectAmplifier = json.getAsJsonObject("Effect").get("Amplifier").getAsInt();
                    spell.EffectScale = json.getAsJsonObject("Effect").get("Scale").getAsFloat();
                    spell.ShowEffectParticles = json.getAsJsonObject("Effect").get("Particles").getAsBoolean();
                    spell.IsEffectVisible = json.getAsJsonObject("Effect").get("Visible").getAsBoolean();
                    
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
