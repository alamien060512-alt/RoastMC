package com.roastmc.fabric.injected.world.level.entity;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.Entity;
import org.bukkit.event.entity.EntityRemoveEvent;

public interface ContextEntityAccess {

    default void setRemoved(Entity.RemovalReason entity_removalreason, EntityRemoveEvent.Cause cause) {
        throw new ContextStateException("Not implemented");
    }
}
