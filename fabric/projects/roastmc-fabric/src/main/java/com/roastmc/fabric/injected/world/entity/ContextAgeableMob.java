package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAgeableMob {

    default boolean roastmc$ageLocked() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setAgeLocked(boolean ageLocked) {
        throw new ContextStateException("Not implemented");
    }
}
