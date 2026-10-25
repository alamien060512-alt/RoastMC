package com.roastmc.fabric.mixin.vanilla.world.clock;

import com.roastmc.fabric.injected.world.clock.ContextServerClockManager;
import net.minecraft.world.clock.ServerClockManager;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerClockManager.class)
public class MixinServerClockManager implements ContextServerClockManager {
}
