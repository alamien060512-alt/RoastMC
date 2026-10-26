package com.roastmc.fabric.mixin.vanilla.world.level.storage;

import com.roastmc.fabric.injected.world.level.storage.ContextLevelStorageAccess;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(LevelStorageSource.LevelStorageAccess.class)
public class MixinLevelStorageSource$LevelStorageAccess implements ContextLevelStorageAccess {
}
