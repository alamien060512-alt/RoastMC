package com.roastmc.fabric.injected.world.level.portal;

import org.bukkit.event.player.PlayerTeleportEvent;

public interface ContextTeleportTransition {

    default PlayerTeleportEvent.TeleportCause roastmc$cause() {
        return PlayerTeleportEvent.TeleportCause.UNKNOWN;
    }

    default void roastmc$cause(PlayerTeleportEvent.TeleportCause cause) {
    }
}
