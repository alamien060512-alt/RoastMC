package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.roastmc.fabric.injected.world.level.block.entity.ContextShulkerBoxBlockEntity;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/3/28 18:51
 */
@Mixin(ShulkerBoxBlockEntity.class)
public abstract class MixinShulkerBoxBlockEntity extends RandomizableContainerBlockEntity implements ContextShulkerBoxBlockEntity {

    // @formatter:off
    @Shadow private NonNullList<ItemStack> itemStacks;
    @Unique public boolean opened;
    @Unique public List<HumanEntity> transaction = new java.util.ArrayList<>();
    @Unique private int maxStack = 99;
    // @formatter:on

    protected MixinShulkerBoxBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @Inject(method = "startOpen", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;blockEvent(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;II)V"))
    private void roastmc_startOpen(ContainerUser containerUser, CallbackInfo ci) {
        if (opened) ci.cancel();
    }

    @Inject(method = "stopOpen", cancellable = true, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;blockEvent(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;II)V"))
    private void roastmc_stopOpen(ContainerUser containerUser, CallbackInfo ci) {
        if (opened) ci.cancel();
    }

    // CraftBukkit start - add fields and methods
    public List<ItemStack> getContents() {
        return this.itemStacks;
    }

    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    public List<HumanEntity> getViewers() {
        return transaction;
    }

    @Override
    public int getMaxStackSize() {
        return maxStack;
    }

    public void setMaxStackSize(int size) {
        maxStack = size;
    }
    // CraftBukkit end

    @Override
    public boolean roastmc_opened() {
        return opened;
    }

    @Override
    public void roastmc_opened(boolean opened) {
        this.opened = opened;
    }
}
