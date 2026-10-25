package com.roastmc.fabric.injected.world.entity.decoration;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextArmorStand {

    default boolean roastmc$canMove() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setCanMove(boolean canMove) {
        throw new ContextStateException("Not implemented");
    }
}
