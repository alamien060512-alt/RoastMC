package com.roastmc.fabric.injected.world.entity.player;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.event.player.PlayerBedEnterEvent;

/**
 * @author Mgazul
 * @date 2026/5/13 21:38
 */
public interface ContextPlayerBedSleepingProblem {

    default PlayerBedEnterEvent.BedEnterResult bukkit() {
        throw new ContextStateException("Not implemented");
    }
}
