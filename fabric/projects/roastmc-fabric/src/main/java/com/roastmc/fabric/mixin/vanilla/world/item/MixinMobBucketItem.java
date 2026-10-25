package com.roastmc.fabric.mixin.vanilla.world.item;

import net.minecraft.world.item.MobBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(MobBucketItem.class)
public class MixinMobBucketItem {

    @Inject(method = "useOn", at = @At("HEAD"))
    private void roastmc_useOn(CallbackInfo ci) {
        // CraftBukkit
    }
}
