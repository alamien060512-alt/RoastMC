package com.roastmc.fabric.mixin.vanilla.world.level.chunk.storage;

import com.roastmc.fabric.injected.world.level.chunk.storage.ContextRegionFileStorage;
import net.minecraft.world.level.chunk.storage.RegionFileStorage;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RegionFileStorage.class)
public class MixinRegionFileStorage implements ContextRegionFileStorage {
}
