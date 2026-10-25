package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextChunkHolder;
import net.minecraft.server.level.ChunkHolder;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ChunkHolder.class)
public class MixinChunkHolder implements ContextChunkHolder {
}
