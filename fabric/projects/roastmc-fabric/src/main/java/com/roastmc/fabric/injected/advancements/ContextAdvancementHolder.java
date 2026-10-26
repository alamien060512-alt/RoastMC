package com.roastmc.fabric.injected.advancements;

import com.roastmc.fabric.util.ContextStateException;

public interface ContextAdvancementHolder {

    default org.bukkit.advancement.Advancement toBukkit() {
        throw new ContextStateException("Not implemented");
    }
}
