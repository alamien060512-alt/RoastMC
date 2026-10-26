package com.roastmc.fabric.injected.world.entity.projectile;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextFishingHook {

    default int roastmc$minLureTime() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$minLureTime(int minLureTime) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$maxLureTime() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxLureTime(int maxLureTime) {
        throw new ContextStateException("Not implemented");
    }

    default float roastmc$minLureAngle() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$minLureAngle(float minLureAngle) {
        throw new ContextStateException("Not implemented");
    }

    default float roastmc$maxLureAngle() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxLureAngle(float maxLureAngle) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$rainInfluenced() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$rainInfluenced(boolean rainInfluenced) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$skyInfluenced() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$skyInfluenced(boolean skyInfluenced) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$minWaitTime() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$minWaitTime(int minWaitTime) {
        throw new ContextStateException("Not implemented");
    }

    default int roastmc$maxWaitTime() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$maxWaitTime(int minWaitTime) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$applyLure() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$applyLure(boolean applyLure) {
        throw new ContextStateException("Not implemented");
    }
}
