package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextChunkMap;
import net.minecraft.server.level.ChunkMap;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ChunkMap.class)
public class MixinChunkMap implements ContextChunkMap {

    // CraftBukkit start - recursion-safe executor for Chunk loadCallback() and unloadCallback()
    public final com.roastmc.fabric.bukkit.BukkitCallbackExecutor roastmc$callbackExecutorField = new com.roastmc.fabric.bukkit.BukkitCallbackExecutor();

    @Override
    public com.roastmc.fabric.bukkit.BukkitCallbackExecutor roastmc$callbackExecutor() {
        return roastmc$callbackExecutorField;
    }
    // CraftBukkit end
}
