package com.roastmc.fabric.mixin.vanilla.server.level;

import com.roastmc.fabric.injected.server.level.ContextServerPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ServerPlayerGameMode.class)
public class MixinServerPlayerGameMode implements ContextServerPlayerGameMode {

    @Shadow
    public boolean firedInteract;

    @Shadow
    public boolean interactResult;

    @Shadow
    public BlockPos interactPosition;

    @Shadow
    public InteractionHand interactHand;

    @Shadow
    public ItemStack interactItemStack;

    @Override
    public boolean roastmc$isFiredInteract() {
        return firedInteract;
    }

    @Override
    public void roastmc$setFiredInteract(boolean firedInteract) {
        this.firedInteract = firedInteract;
    }

    @Override
    public boolean roastmc$getInteractResult() {
        return interactResult;
    }

    @Override
    public BlockPos roastmc$getinteractPosition() {
        return interactPosition;
    }

    @Override
    public InteractionHand roastmc$getinteractHand() {
        return interactHand;
    }

    @Override
    public ItemStack roastmc$getinteractItemStack() {
        return interactItemStack;
    }
}
