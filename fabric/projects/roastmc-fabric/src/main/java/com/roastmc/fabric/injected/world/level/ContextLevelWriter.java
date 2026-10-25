package com.roastmc.fabric.injected.world.level;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;

public interface ContextLevelWriter {

    default boolean addFreshEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new ContextStateException("Not implemented");
    }
}
