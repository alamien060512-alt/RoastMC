package com.roastmc.fabric.mixin.vanilla.world.item;

import net.minecraft.world.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SpawnEggItem.class)
public class MixinSpawnEggItem {

    @Inject(method = "useOn", at = @At("HEAD"))
    private void roastmc_useOn(CallbackInfo ci) {
        // CraftBukkit
    }
}
