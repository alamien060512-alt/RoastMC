package com.roastmc.fabric.injected.network.syncher;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.network.syncher.EntityDataAccessor;

public interface ContextSynchedEntityData {

    default <T> void markDirty(EntityDataAccessor<T> entitydataaccessor) {
        throw new ContextStateException("Not implemented");
    }
}
