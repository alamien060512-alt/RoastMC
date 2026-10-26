package com.roastmc.fabric.mixin.vanilla.world.entity.monster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.phys.Vec3;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ElderGuardian.class)
public class MixinElderGuardian {

    @Redirect(method = "customServerAiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectUtil;addEffectToPlayersAround(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;DLnet/minecraft/world/effect/MobEffectInstance;I)Ljava/util/List;"))
    private List<ServerPlayer> roastmc$customServerAiStep(ServerLevel level, net.minecraft.world.entity.Entity entity, Vec3 pos, double radius, MobEffectInstance effect, int duration) {
        return net.minecraft.world.effect.MobEffectUtil.addEffectToPlayersAround(level, entity, pos, radius, effect, duration);
    }
}
