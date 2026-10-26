package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import net.minecraft.world.level.block.entity.CommandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(CommandBlockEntity.class)
public class MixinCommandBlockEntity {

    @Inject(method = "tick", at = @At("HEAD"))
    private void roastmc_tick(CallbackInfo ci) {
        // CraftBukkit
    }
}
