package com.roastmc.fabric.injected.world.entity.animal.equine;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAbstractHorse {

    default int roastmc$maxDomestication() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxDomestication(int maxDomestication) {
        throw new ContextStateException("Not implemented");
    }
}
