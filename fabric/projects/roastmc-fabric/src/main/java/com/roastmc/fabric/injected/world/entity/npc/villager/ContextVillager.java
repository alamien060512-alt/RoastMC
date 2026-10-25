package com.roastmc.fabric.injected.world.entity.npc.villager;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextVillager {

    default long roastmc$gossipDecayInterval() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$gossipDecayInterval(long gossipDecayInterval) {
        throw new ContextStateException("Not implemented");
    }
}
