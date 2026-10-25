package com.roastmc.fabric.mixin.vanilla.server.commands;

import com.roastmc.fabric.injected.world.entity.ContextEntity;
import java.util.Set;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/10/24
 */
@Mixin(TeleportCommand.class)
public class MixinTeleportCommand {

    // CraftBukkit start - Teleport event
    @Redirect(method = "performTeleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFZ)Z"))
    private static boolean roastmc$teleportWithCause(Entity victim, ServerLevel level, double x, double y, double z, Set<Relative> relatives, float yRot, float xRot, boolean resetCamera) {
        return ((ContextEntity) victim).teleportTo(level, x, y, z, relatives, yRot, xRot, resetCamera, PlayerTeleportEvent.TeleportCause.COMMAND);
    }
    // CraftBukkit end
}
