package com.roastmc.fabric.injected.world.clock;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.Holder;
import net.minecraft.world.clock.WorldClock;
import org.bukkit.event.world.TimeSkipEvent;

/**
 * @author Mgazul
 * @date 2026/5/13 22:00
 */
public interface ContextServerClockManager {

    default  void setTotalTicks(Holder<WorldClock> clock, long totalTicks, TimeSkipEvent.SkipReason reason) {
        throw new ContextStateException("Not implemented");
    }
}
