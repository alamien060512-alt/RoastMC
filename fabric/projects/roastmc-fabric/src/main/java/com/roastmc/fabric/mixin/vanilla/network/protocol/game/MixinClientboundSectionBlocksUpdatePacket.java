package com.roastmc.fabric.mixin.vanilla.network.protocol.game;

import com.roastmc.fabric.injected.network.protocol.game.ContextClientboundSectionBlocksUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/5/13 21:06
 */
@Mixin(ClientboundSectionBlocksUpdatePacket.class)
public class MixinClientboundSectionBlocksUpdatePacket implements ContextClientboundSectionBlocksUpdatePacket {

    // @formatter:off
    @Mutable @Shadow @Final private BlockState[] states;
    // @formatter:on

    @Override
    public void putbukkitExtra(BlockState[] blockStates) {
        states = blockStates;
    }
}
