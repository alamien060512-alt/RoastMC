package com.roastmc.fabric.mixin.vanilla.world.effect;

import net.minecraft.world.effect.RegenerationMobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RegenerationMobEffect.class)
public class MixinRegenerationMobEffect {

    @Redirect(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    private void roastmc_heal(LivingEntity mob, float amount) {
        mob.heal(amount, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.MAGIC_REGEN); // CraftBukkit
    }
}
