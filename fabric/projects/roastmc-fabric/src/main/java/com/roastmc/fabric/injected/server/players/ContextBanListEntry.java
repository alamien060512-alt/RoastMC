package com.roastmc.fabric.injected.server.players;

import com.roastmc.fabric.util.ContextStateException;
import java.util.Date;

public interface ContextBanListEntry {

    default Date getCreated() {
        throw new ContextStateException("Not implemented");
    }
}
