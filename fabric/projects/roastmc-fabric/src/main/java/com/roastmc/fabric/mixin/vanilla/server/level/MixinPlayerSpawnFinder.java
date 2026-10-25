package com.roastmc.fabric.mixin.vanilla.server.level;

import net.minecraft.server.level.PlayerSpawnFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(PlayerSpawnFinder.class)
public class MixinPlayerSpawnFinder {

    @Inject(method = "findSpawn", at = @At("HEAD"))
    private void roastmc_findSpawn(CallbackInfo ci) {
        // CraftBukkit
    }
}
