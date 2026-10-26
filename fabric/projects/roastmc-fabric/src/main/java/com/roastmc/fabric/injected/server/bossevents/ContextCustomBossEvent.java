package com.roastmc.fabric.injected.server.bossevents;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.boss.KeyedBossBar;

public interface ContextCustomBossEvent {

    default KeyedBossBar getBukkitEntity() {
        throw new ContextStateException("Not implemented");
    }
}
