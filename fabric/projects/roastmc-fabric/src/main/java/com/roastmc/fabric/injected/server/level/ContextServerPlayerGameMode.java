package com.roastmc.fabric.injected.server.level;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public interface ContextServerPlayerGameMode {

    default boolean roastmc$isFiredInteract() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$setFiredInteract(boolean firedInteract) {
        throw new ContextStateException("Not implemented");
    }

    default boolean roastmc$getInteractResult() {
        throw new ContextStateException("Not implemented");
    }

    default BlockPos roastmc$getinteractPosition() {
        throw new ContextStateException("Not implemented");
    }

    default InteractionHand roastmc$getinteractHand() {
        throw new ContextStateException("Not implemented");
    }

    default ItemStack roastmc$getinteractItemStack() {
        throw new ContextStateException("Not implemented");
    }

    default boolean destroyBlock(BlockPos pos, int ack) {
        return false;
    }
}
