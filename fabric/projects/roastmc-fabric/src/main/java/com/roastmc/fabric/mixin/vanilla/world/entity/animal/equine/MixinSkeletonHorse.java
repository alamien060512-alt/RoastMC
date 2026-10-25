package com.roastmc.fabric.mixin.vanilla.world.entity.animal.equine;

import net.minecraft.world.entity.animal.equine.SkeletonHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SkeletonHorse.class)
public class MixinSkeletonHorse {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/equine/SkeletonHorse;discard()V"))
    private void roastmc_discard(SkeletonHorse horse) {
        horse.discard(org.bukkit.event.entity.EntityRemoveEvent.Cause.DESPAWN);
    }
}
