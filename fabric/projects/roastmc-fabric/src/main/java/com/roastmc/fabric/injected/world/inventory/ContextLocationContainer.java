package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.Location;

public interface ContextLocationContainer {

    default Location getLocation() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setLocation(Location location) {
        throw new ContextStateException("Not implemented");
    }
}
