package com.roastmc.fabric.mixin.vanilla.world.entity.ai.behavior.warden;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.behavior.warden.Digging;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Digging.class)
public class MixinDigging {

    @Redirect(method = "stop", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V"))
    private void roastmc_remove(Entity entity, Entity.RemovalReason reason) {
        entity.remove(Entity.RemovalReason.DISCARDED, org.bukkit.event.entity.EntityRemoveEvent.Cause.DESPAWN);
    }
}
