package com.roastmc.fabric.injected.world.level.storage;

import com.roastmc.fabric.util.ContextStateException;
import java.io.IOException;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.validation.ContentValidationException;

public interface ContextLevelStorageSource {

    default LevelStorageSource.LevelStorageAccess validateAndCreateAccess(String string, ResourceKey<LevelStem> dimensionType) throws IOException, ContentValidationException { // CraftBukkit
        throw new ContextStateException("Not implemented");
    }
}
