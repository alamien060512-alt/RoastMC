package com.roastmc.fabric.injected.world.entity.vehicle.minecart;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextMinecartTNT {

    default boolean roastmc$isIncendiary() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$isIncendiary(boolean isIncendiary) {
        throw new ContextStateException("Not implemented");
    }
}
