package com.roastmc.fabric.mixin.vanilla.world.entity.projectile.throwableitemprojectile;

import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownLingeringPotion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ThrownLingeringPotion.class)
public class MixinThrownLingeringPotion {

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/throwableitemprojectile/ThrownLingeringPotion;discard()V"))
    private void roastmc_discard(ThrownLingeringPotion potion) {
        potion.discard(org.bukkit.event.entity.EntityRemoveEvent.Cause.HIT); // CraftBukkit - add Bukkit remove cause
    }
}
