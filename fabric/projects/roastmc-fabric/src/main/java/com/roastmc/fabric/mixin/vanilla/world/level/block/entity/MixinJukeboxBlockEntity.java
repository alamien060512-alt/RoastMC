package com.roastmc.fabric.mixin.vanilla.world.level.block.entity;

import com.google.common.annotations.VisibleForTesting;
import com.roastmc.fabric.injected.world.level.block.entity.ContextJukeboxBlockEntity;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/4/2 19:18
 */
@Mixin(JukeboxBlockEntity.class)
public abstract class MixinJukeboxBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem, ContextJukeboxBlockEntity {

    @Shadow private ItemStack item;
    @Shadow @Final private JukeboxSongPlayer jukeboxSongPlayer;
    // CraftBukkit start - add fields and methods
    public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();
    private int maxStack = MAX_STACK;
    public boolean opened;

    public MixinJukeboxBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @Override
    public List<ItemStack> getContents() {
        return Collections.singletonList(item);
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return transaction;
    }

    @Override
    public void setMaxStackSize(int size) {
        maxStack = size;
    }

    @Override
    public Location getLocation() {
        if (level == null) return null;
        return new org.bukkit.Location(level.getWorld(), worldPosition.getX(), worldPosition.getY(), worldPosition.getZ());
    }
    // CraftBukkit end

    @VisibleForTesting
    @Override
    public void setSongItemWithoutPlaying(ItemStack itemStack, long ticksSinceSongStarted) { // CraftBukkit - add argument
        this.item = itemStack;
        this.jukeboxSongPlayer.song = null; // CraftBukkit - reset
        JukeboxSong.fromStack(itemStack).ifPresent((holder) -> {
            this.jukeboxSongPlayer.setSongWithoutPlaying(holder, ticksSinceSongStarted); // CraftBukkit - add argument
        });
        // CraftBukkit start - add null check for level
        if (level != null) {
            this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        }
        // CraftBukkit end
        this.setChanged();
    }
}
