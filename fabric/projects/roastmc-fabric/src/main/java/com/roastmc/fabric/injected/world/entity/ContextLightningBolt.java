package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextLightningBolt {

    default boolean roastmc$isSilent() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setIsSilent(boolean isSilent) {
        throw new ContextStateException("Not implemented");
    }
}
