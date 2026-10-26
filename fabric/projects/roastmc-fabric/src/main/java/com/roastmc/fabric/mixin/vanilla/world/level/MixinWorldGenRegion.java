package com.roastmc.fabric.mixin.vanilla.world.level;

import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(WorldGenRegion.class)
public class MixinWorldGenRegion {

    @Redirect(method = "addFreshEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/WorldGenRegion;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean roastmc_addFreshEntity(WorldGenRegion region, Entity entity) {
        return region.addFreshEntity(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.DEFAULT); // CraftBukkit
    }
}
