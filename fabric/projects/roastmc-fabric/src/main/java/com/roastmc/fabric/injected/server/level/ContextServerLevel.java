package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.injected.world.level.ContextLevel;
import com.roastmc.fabric.util.ContextStateException;
import java.util.UUID;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.jspecify.annotations.Nullable;

public interface ContextServerLevel extends ContextLevel {

    default boolean addEntitySerialized(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default  <T extends ParticleOptions> int sendParticles(T type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, boolean force) {
        throw new ContextStateException("Not implemented");
    }

    default LevelStorageSource.LevelStorageAccess roastmc_storageSource() {
        throw new ContextStateException("Not implemented");
    }

    default UUID roastmc$uuid() {
        throw new ContextStateException("Not implemented");
    }

    default LevelChunk getChunkIfLoaded(int x, int z) {
        throw new ContextStateException("Not implemented");
    }

    default boolean addWithUUID(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default void addDuringTeleport(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default boolean tryAddFreshEntityWithPassengers(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default boolean strikeLightning(Entity entitylightning) {
        throw new ContextStateException("Not implemented");
    }

    default boolean strikeLightning(Entity entitylightning, LightningStrikeEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default  <T extends ParticleOptions> int sendParticles(ServerPlayer sender, T t0, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6, boolean force) {
        throw new ContextStateException("Not implemented");
    }

    default PrimaryLevelData roastmc$serverLevelDataCB() {
        throw new ContextStateException("Not implemented");
    }

    default boolean canAddFreshEntity() {
        throw new ContextStateException("Not implemented");
    }

    default void sendParticlesSource(ServerPlayer sender, ParticleOptions particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        throw new ContextStateException("Not implemented");
    }

    default WorldGenSettings getWorldGenSettings() {
        throw new ContextStateException("Not implemented");
    }

    default ServerExplosion explode0(@Nullable Entity source, @Nullable DamageSource damageSource, @Nullable ExplosionDamageCalculator damageCalculator, double x, double y, double z, float r, boolean fire, Level.ExplosionInteraction interactionType, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, WeightedList<ExplosionParticleInfo> blockParticles, Holder<SoundEvent> explosionSound) {
        throw new ContextStateException("Not implemented");
    }
}
