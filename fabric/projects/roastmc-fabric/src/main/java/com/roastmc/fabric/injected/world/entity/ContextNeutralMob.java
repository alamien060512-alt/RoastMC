package com.roastmc.fabric.injected.world.entity;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public interface ContextNeutralMob {

    default boolean setTarget(@Nullable LivingEntity entityliving, org.bukkit.event.entity.EntityTargetEvent.TargetReason reason, boolean fireEvent) { // CraftBukkit
        throw new ContextStateException("Not implemented");
    }
}
