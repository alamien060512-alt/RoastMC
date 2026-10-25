package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.bukkit.BukkitUtils;
import com.roastmc.fabric.injected.server.level.ContextTicketType;
import net.minecraft.server.level.TicketType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/3/28 20:14
 */

@Mixin(TicketType.class)
public abstract class MixinTicketType implements ContextTicketType {

    @Shadow public long timeout;

    @Override
    @Overwrite
    public long timeout() {
        return (((TicketType)(Object)this) != PLUGIN) ? this.timeout : BukkitUtils.pluginTimeout;
    }
}
