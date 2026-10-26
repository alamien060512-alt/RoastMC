package com.roastmc.fabric.mixin.vanilla.server.network;

import com.roastmc.fabric.injected.server.network.ContextServerLoginPacketListenerImpl;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerLoginPacketListenerImpl.class)
public class MixinServerLoginPacketListenerImpl implements ContextServerLoginPacketListenerImpl {
}
