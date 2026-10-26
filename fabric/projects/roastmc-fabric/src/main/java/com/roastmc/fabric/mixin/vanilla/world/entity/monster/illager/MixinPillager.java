package com.roastmc.fabric.mixin.vanilla.world.entity.monster.illager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.illager.Pillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Pillager.class)
public class MixinPillager {

    @Redirect(method = "pickUpItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;discard()V"))
    private void roastmc_discard(Entity entity) {
        entity.discard(org.bukkit.event.entity.EntityRemoveEvent.Cause.PICKUP);
    }
}
