package com.roastmc.fabric.mixin.vanilla.server.commands;

import net.minecraft.server.commands.SetSpawnCommand;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SetSpawnCommand.class)
public class MixinSetSpawnCommand {

    @Redirect(method = "setSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setRespawnPosition(Lnet/minecraft/server/level/ServerPlayer$RespawnConfig;Z)V"))
    private static void roastmc_setSpawn(ServerPlayer player, net.minecraft.server.level.ServerPlayer.RespawnConfig config, boolean forced) {
        player.setRespawnPosition(config, forced, org.bukkit.event.player.PlayerSpawnChangeEvent.Cause.COMMAND); // CraftBukkit
    }
}
