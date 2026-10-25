package com.roastmc.fabric.injected.network.protocol.game;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author Mgazul by MohistMC
 * @date 2023/5/6 20:46:36
 */
public interface ContextClientboundSectionBlocksUpdatePacket {

    default void putbukkitExtra(BlockState[] states) {
        throw new ContextStateException("Not implemented");
    }
}
