package com.roastmc.fabric.injected.world.damagesource;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.bukkit.block.Block;

public interface ContextDamageSource {

    default boolean isSweep() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource sweep() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isMelting() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource melting() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isPoison() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource poison() {
        throw new ContextStateException("Not implemented");
    }

    default Entity getDamager() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource customEntityDamager(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default Entity getCausingDamager() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource customCausingEntityDamager(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCustomCausingEntityDamager(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default Block getDirectBlock() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource directBlock(Block block) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setDirectBlock(Block block) {
        throw new ContextStateException("Not implemented");
    }

    default org.bukkit.block.BlockState getDirectBlockState() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource directBlockState(org.bukkit.block.BlockState blockState) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setDirectBlockState(org.bukkit.block.BlockState block) {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource cloneInstance() {
        throw new ContextStateException("Not implemented");
    }

    default Entity getCausingEntity() {
        throw new ContextStateException("Not implemented");
    }

    default DamageSource directBlock(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos blockPosition) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCustomEntityDamager(Entity entity) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setSweep(boolean sweep) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setPoison(boolean poison) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setMelting(boolean melting) {
        throw new ContextStateException("Not implemented");
    }
}
