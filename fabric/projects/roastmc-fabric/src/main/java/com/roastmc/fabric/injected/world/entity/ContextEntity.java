package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.injected.world.level.entity.ContextEntityAccess;
import com.roastmc.fabric.util.ContextStateException;
import java.util.Set;
import net.minecraft.commands.CommandSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueOutput;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.event.CraftPortalEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityRemoveEvent;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.Nullable;

public interface ContextEntity extends ContextEntityAccess {

    default boolean roastmc_inWorld() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_inWorld(boolean inWorld) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setBukkitEntity(CraftEntity bukkitEntity) {
        throw new ContextStateException("Not implemented");
    }

    default void refreshEntityData(ServerPlayer to) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_persist() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_persist(boolean persist) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_visibleByDefault() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_visibleByDefault(boolean visibleByDefault) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_valid() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_valid(boolean valid) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc_maxAirTicks() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_maxAirTicks(int maxAirTicks) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.projectiles.ProjectileSource roastmc_projectileSource() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_projectileSource(org.bukkit.projectiles.ProjectileSource projectileSource) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$lastDamageCancelled() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$lastDamageCancelled(boolean lastDamageCancelled) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_persistentInvisibility() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_persistentInvisibility(boolean persistentInvisibility) {
        throw new ContextStateException("Not implemented");
    }

    default BlockPos roastmc$lastLavaContact() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setLastLavaContact(BlockPos lastLavaContact) {
        throw new ContextStateException("Not implemented");
    }

    default CraftEntity getBukkitEntity() {
        throw new ContextStateException("Not implemented");
    }

    default int getDefaultMaxAirSupply() {
        throw new ContextStateException("Not implemented");
    }

    default float getBukkitYaw() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isChunkLoaded() {
        throw new ContextStateException("Not implemented");
    }

    default void postTick() {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getSwimSound0() {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getSwimSplashSound0() {
        throw new ContextStateException("Not implemented");
    }

    default SoundEvent getSwimHighSpeedSplashSound0() {
        throw new ContextStateException("Not implemented");
    }

    default boolean canCollideWithBukkit(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default boolean teleportTo(ServerLevel worldserver, double d0, double d1, double d2, Set<Relative> set, float f, float f1, boolean flag, PlayerTeleportEvent.TeleportCause cause) {
        throw new ContextStateException("Not implemented");
    }

    default long roastmc$activatedTick() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setActivatedTick(long activatedTick) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$defaultActivationState() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setDefaultActivationState(boolean state) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_generation() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_generation(boolean gen) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$removePassenger(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default CraftPortalEvent callPortalEvent(Entity entity, Location exit, PlayerTeleportEvent.TeleportCause cause, int searchRadius, int creationRadius) {
        throw new ContextStateException("Not implemented");
    }

    default void discard(EntityRemoveEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void remove(Entity.RemovalReason entity_removalreason, EntityRemoveEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    @Override
    default void setRemoved(Entity.RemovalReason entity_removalreason, EntityRemoveEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void pushRemoveCause(EntityRemoveEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }

    default void pushSpawnCause(CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default void igniteForSeconds(float i, boolean callEvent) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_pluginRemoved() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_pluginRemoved(boolean pluginRemoved) {
        throw new ContextStateException("Not implemented");
    }

    default boolean saveAsPassenger(ValueOutput valueoutput, boolean includeAll) {
        throw new ContextStateException("Not implemented");
    }

    default void saveWithoutId(ValueOutput valueoutput, boolean includeAll) {
        throw new ContextStateException("Not implemented");
    }

    default void addAdditionalSaveData(ValueOutput valueOutput, boolean includeAll) {
        throw new ContextStateException("Not implemented");
    }

    default void pushUnleashReason(EntityUnleashEvent.UnleashReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default boolean dropAllLeashConnections(@Nullable Player player, EntityUnleashEvent.UnleashReason reason) {
        throw new ContextStateException("Not implemented");
    }

    default CommandSource roastmc$commandSource() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCommandSource(CommandSource commandSource) {
        throw new ContextStateException("Not implemented");
    }

    default void inactiveTick() {
        throw new ContextStateException("Not implemented");
    }
}
