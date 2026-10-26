package com.roastmc.fabric.mixin.vanilla.world.entity.vehicle.minecart;

import net.minecraft.world.entity.vehicle.minecart.MinecartCommandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(MinecartCommandBlock.class)
public class MixinMinecartCommandBlock {

    @Inject(method = "activateMinecart", at = @At("HEAD"))
    private void roastmc_activateMinecart(CallbackInfo ci) {
        // CraftBukkit
    }
}
