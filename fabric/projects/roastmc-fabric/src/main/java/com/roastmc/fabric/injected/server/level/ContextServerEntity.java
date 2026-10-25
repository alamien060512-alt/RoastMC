package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.util.ContextStateException;
import java.util.Set;
import net.minecraft.server.network.ServerPlayerConnection;

public interface ContextServerEntity {

    default void setTrackedPlayers(Set<ServerPlayerConnection> trackedPlayers) {
        throw new ContextStateException("Not implemented");
    }
}
