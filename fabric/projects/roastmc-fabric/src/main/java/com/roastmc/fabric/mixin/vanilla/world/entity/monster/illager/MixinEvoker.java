package com.roastmc.fabric.mixin.vanilla.world.entity.monster.illager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.illager.Evoker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Evoker.class)
public class MixinEvoker {

    @Redirect(method = "performSpellCasting", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z"))
    private void roastmc_addFreshEntityWithPassengers(ServerLevel level, Entity entity) {
        level.addFreshEntityWithPassengers(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SPELL);
    }
}
