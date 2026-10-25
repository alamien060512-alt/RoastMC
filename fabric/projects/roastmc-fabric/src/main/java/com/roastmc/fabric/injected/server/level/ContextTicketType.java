package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.server.level.TicketType;

/**
 * @author Mgazul
 * @date 2026/3/28 20:18
 */
public interface ContextTicketType {

    TicketType PLUGIN = TicketType.register("plugin", 0L, 14);
    TicketType PLUGIN_TICKET = TicketType.register("plugin_ticket", 0L, 14);

    default long timeout() {
        throw new ContextStateException("Not implemented");
    }
}
