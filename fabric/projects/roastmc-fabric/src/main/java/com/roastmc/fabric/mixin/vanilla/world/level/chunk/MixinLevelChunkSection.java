package com.roastmc.fabric.mixin.vanilla.world.level.chunk;

import com.roastmc.fabric.injected.world.level.chunk.ContextLevelChunkSection;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(LevelChunkSection.class)
public class MixinLevelChunkSection implements ContextLevelChunkSection {
}
