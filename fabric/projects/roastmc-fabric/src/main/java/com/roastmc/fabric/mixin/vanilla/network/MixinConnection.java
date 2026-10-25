package com.roastmc.fabric.mixin.vanilla.network;

import com.roastmc.fabric.injected.network.ContextConnection;
import java.net.SocketAddress;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Connection.class)
public abstract class MixinConnection implements ContextConnection {

    @Shadow
    public String hostname;

    @Shadow
    public abstract SocketAddress getRemoteAddress();

    @Override
    public String roastmc$hostname() {
        return hostname;
    }

    @Override
    public void roastmc$setHostName(String hostName) {
        this.hostname = hostName;
    }

    @Override
    public SocketAddress getRawAddress() {
        return getRemoteAddress();
    }
}
