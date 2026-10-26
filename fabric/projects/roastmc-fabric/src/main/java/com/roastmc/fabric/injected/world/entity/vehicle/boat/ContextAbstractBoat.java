package com.roastmc.fabric.injected.world.entity.vehicle.boat;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAbstractBoat {

    default double roastmc_maxSpeed() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_maxSpeed(double maxSpeed) {
        throw new ContextStateException("Not implemented");
    }

    default double roastmc_occupiedDeceleration() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_occupiedDeceleration(double occupiedDeceleration) {
        throw new ContextStateException("Not implemented");
    }

    default double roastmc_unoccupiedDeceleration() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_unoccupiedDeceleration(double occupiedDeceleration) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc_landBoats() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc_landBoats(boolean landBoats) {
        throw new ContextStateException("Not implemented");
    }
}
