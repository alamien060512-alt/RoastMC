package com.roastmc.fabric.mixin.vanilla.world.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InfestedMobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(InfestedMobEffect.class)
public class MixinInfestedMobEffect {

    @Inject(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"), cancellable = true)
    private void roastmc_applyEffectTick(ServerLevel level, LivingEntity mob, int amplification, CallbackInfoReturnable<Boolean> cir) {
        if (!level.addFreshEntity(null, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.POTION_EFFECT)) { // CraftBukkit
            cir.setReturnValue(true);
        }
    }
}
