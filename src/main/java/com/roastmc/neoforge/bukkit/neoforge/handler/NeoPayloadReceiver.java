package com.roastmc.neoforge.bukkit.neoforge.handler;

import com.roastmc.neoforge.bukkit.neoforge.payload.ChannelPayloadImpl;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

public interface NeoPayloadReceiver extends ChannelWorker, IPayloadHandler<ChannelPayloadImpl> {
}