package com.roastmc.fabric.mixin.vanilla.world.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.OozingMobEffect;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(OozingMobEffect.class)
public class MixinOozingMobEffect {

    @Redirect(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean roastmc_addFreshEntity(ServerLevel level, Entity entity) {
        return level.addFreshEntity(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.POTION_EFFECT); // CraftBukkit
    }
}
