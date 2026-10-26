package com.roastmc.fabric.injected.world.entity.animal.equine;

import com.roastmc.fabric.util.ContextStateException;

/**
 * @author Mgazul
 * @date 2026/5/1 18:25
 */
public interface ContextLlama {

    default void setStrengthPublic(int i) {
        throw new ContextStateException("Not implemented");
    }
}
