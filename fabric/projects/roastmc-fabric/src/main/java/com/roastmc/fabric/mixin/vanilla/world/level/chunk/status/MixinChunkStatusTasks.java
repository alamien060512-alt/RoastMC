package com.roastmc.fabric.mixin.vanilla.world.level.chunk.status;

import net.minecraft.world.level.chunk.status.ChunkStatusTasks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ChunkStatusTasks.class)
public class MixinChunkStatusTasks {

    @Inject(method = "generate", at = @At("HEAD"))
    private void roastmc_generate(CallbackInfo ci) {
        // CraftBukkit
    }
}
