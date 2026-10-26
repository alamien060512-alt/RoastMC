package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAnvilMenu {

    default int roastmc$getDeniedCost() {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$maximumRepairCost() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maximumRepairCost(int maximumRepairCost) {
        throw new ContextStateException("Not implemented");
    }
}
