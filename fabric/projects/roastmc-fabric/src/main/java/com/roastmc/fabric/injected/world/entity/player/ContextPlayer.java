package com.roastmc.fabric.injected.world.entity.player;

import com.roastmc.fabric.injected.world.entity.ContextLivingEntity;
import com.roastmc.fabric.util.ContextStateException;
import com.mojang.datafixers.util.Either;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.player.PlayerSpawnChangeEvent;

public interface ContextPlayer extends ContextLivingEntity {

    default boolean roastmc$affectsSpawning() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setAffectsSpawning(boolean affectsSpawning) {
        throw new ContextStateException("Not implemented");
    }

    @Override
    default CraftHumanEntity getBukkitEntity() {
        throw new ContextStateException("Not implemented");
    }

    default void pushExhaustReason(EntityExhaustionEvent.ExhaustionReason reason) {
    }

    default Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos blockposition, boolean force) {
        throw new ContextStateException("Not implemented");
    }

    default void causeFoodExhaustion(float f, EntityExhaustionEvent.ExhaustionReason reason) {
    }

    default Entity getEntityOnShoulder(CompoundTag nbttagcompound) { // CraftBukkit void->boolean
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$fauxSleeping() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$fauxSleeping(boolean fauxSleeping) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$oldLevel() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setOldLevel(int oldLevel) {
        throw new ContextStateException("Not implemented");
    }

    default Player forceSleepInBed(boolean force) {
        throw new ContextStateException("Not implemented");
    }

    default AtomicBoolean roastmc$startSleepInBed_force() {
        throw new ContextStateException("Not implemented");
    }

    default void pushSpawnChangeCause(PlayerSpawnChangeEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }
}
