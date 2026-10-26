package com.roastmc.fabric.injected.world.entity.vehicle.minecart;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.util.Vector;

public interface ContextAbstractMinecart {

    default boolean roastmc_slowWhenEmpty() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_slowWhenEmpty(boolean slowWhenEmpty) {
        throw new ContextStateException("Not implemented");
    }

    default double roastmc_maxSpeed() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_maxSpeed(double maxSpeed) {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_powRailAccelMult(double powRailAccelMult) {
        throw new ContextStateException("Not implemented");
    }

    default double roastmc_powRailAccelMult() {
        throw new ContextStateException("Not implemented");
    }

    default Vector getFlyingVelocityMod() {
        throw new IllegalStateException("Not implemented");
    }

    default void setFlyingVelocityMod(Vector flying) {
        throw new IllegalStateException("Not implemented");
    }

    default Vector getDerailedVelocityMod() {
        throw new IllegalStateException("Not implemented");
    }

    default void setDerailedVelocityMod(Vector derailed) {
        throw new IllegalStateException("Not implemented");
    }
}
