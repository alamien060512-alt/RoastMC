package com.roastmc.fabric.mixin.vanilla.world.entity.ai.behavior.frog;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.frog.ShootTongue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShootTongue.class)
public class MixinShootTongue {

    @Redirect(method = "eatEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V"))
    private void roastmc_remove(Entity entity, Entity.RemovalReason reason) {
        entity.remove(Entity.RemovalReason.KILLED, org.bukkit.event.entity.EntityRemoveEvent.Cause.DEATH);
    }
}
