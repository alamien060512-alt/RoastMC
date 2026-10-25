package com.roastmc.fabric.injected.util;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextTickThrottler {

    default boolean isIncrementAndUnderThreshold() {
        throw new ContextStateException("Not implemented");
    }

    default boolean isIncrementAndUnderThreshold(int incrementStep, int threshold) {
        throw new ContextStateException("Not implemented");
    }
}
