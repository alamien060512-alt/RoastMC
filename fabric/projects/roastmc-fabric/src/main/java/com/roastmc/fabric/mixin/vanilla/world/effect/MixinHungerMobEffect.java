package com.roastmc.fabric.mixin.vanilla.world.effect;

import net.minecraft.world.effect.HungerMobEffect;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HungerMobEffect.class)
public class MixinHungerMobEffect {

    @Redirect(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    private void roastmc_causeFoodExhaustion(Player player, float amount) {
        player.causeFoodExhaustion(amount, org.bukkit.event.entity.EntityExhaustionEvent.ExhaustionReason.HUNGER_EFFECT);
    }
}
