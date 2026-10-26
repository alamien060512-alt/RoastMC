package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextTicket;
import io.izzel.arclight.mixin.TransformAccess;
import net.minecraft.server.level.Ticket;
import net.minecraft.server.level.TicketType;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/3/28 20:33
 */
@Mixin(Ticket.class)
public class MixinTicket implements ContextTicket {

    // CraftBukkit start
    @Unique
    public Object key;

    @Override
    public void roastmc_key(Object key) {
       this.key = key;
    }
    // CraftBukkit end

    @Unique
    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    private static Ticket of(TicketType tickettype, int i, Object key) {
        return ContextTicket.of(tickettype, i, key);
    }
}
