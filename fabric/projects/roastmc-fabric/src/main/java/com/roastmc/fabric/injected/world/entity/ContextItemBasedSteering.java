package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextItemBasedSteering {

    default void setBoostTicks(int ticks) {
        throw new ContextStateException("Not implemented");
    }
}
