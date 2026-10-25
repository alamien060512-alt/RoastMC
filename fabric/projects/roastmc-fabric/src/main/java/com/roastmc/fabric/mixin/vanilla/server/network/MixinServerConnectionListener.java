package com.roastmc.fabric.mixin.vanilla.server.network;

import com.roastmc.fabric.injected.server.network.ContextServerConnectionListener;
import net.minecraft.server.network.ServerConnectionListener;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerConnectionListener.class)
public class MixinServerConnectionListener implements ContextServerConnectionListener {
}
