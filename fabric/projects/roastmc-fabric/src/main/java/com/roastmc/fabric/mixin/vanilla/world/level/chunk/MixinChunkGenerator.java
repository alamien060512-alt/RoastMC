package com.roastmc.fabric.mixin.vanilla.world.level.chunk;

import com.roastmc.fabric.injected.world.level.chunk.ContextChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ChunkGenerator.class)
public class MixinChunkGenerator implements ContextChunkGenerator {
}
