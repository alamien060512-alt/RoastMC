package com.roastmc.fabric.injected.world.level.portal;

import net.minecraft.world.entity.Entity;

public interface ContextPortalForcer {

    default void pushPortalCreate(Entity entity, int createRadius) {

    }

    default void pushSearchRadius(int searchRadius) {

    }
}
