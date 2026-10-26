package com.roastmc.neoforge.bukkit.neoforge.handler;

import com.roastmc.neoforge.bukkit.neoforge.channel.ChannelContext;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;

public interface ChannelWorker {
    ChannelContext<?> context();

    void synchronize();

    void send(Plugin src, CraftPlayer dst, byte[] data);
}