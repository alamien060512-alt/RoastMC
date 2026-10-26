package com.roastmc.fabric.mixin.vanilla.server.network;

import com.roastmc.fabric.injected.server.network.ContextServerGamePacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerGamePacketListenerImpl.class)
public class MixinServerGamePacketListenerImpl implements ContextServerGamePacketListenerImpl {
}
