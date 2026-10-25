package com.roastmc.fabric.mixin.vanilla.server.commands;

import net.minecraft.server.commands.ListPlayersCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ListPlayersCommand.class)
public class MixinListPlayersCommand {

    @Redirect(method = "format", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;getPlayers()Ljava/util/List;"))
    private static List<ServerPlayer> roastmc_filterPlayers(net.minecraft.server.players.PlayerList playerList, CommandSourceStack source) {
        List<ServerPlayer> players = playerList.getPlayers();
        if (source.getBukkitSender() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player sender = (org.bukkit.entity.Player) source.getBukkitSender();
            return players.stream().filter((ep) -> sender.canSee(ep.getBukkitEntity())).collect(java.util.stream.Collectors.toList());
        }
        return players; // CraftBukkit
    }
}
