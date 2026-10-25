package com.roastmc.fabric.mixin.vanilla.server.rcon;

import com.roastmc.fabric.injected.server.rcon.ContextRconConsoleSource;
import net.minecraft.server.rcon.RconConsoleSource;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(RconConsoleSource.class)
public class MixinRconConsoleSource implements ContextRconConsoleSource {
}
