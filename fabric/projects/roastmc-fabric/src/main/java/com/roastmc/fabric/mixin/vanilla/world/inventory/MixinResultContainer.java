package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextLocationContainer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(ResultContainer.class)
public class MixinResultContainer implements ContextLocationContainer {

    // @formatter:off
    @Shadow private NonNullList<ItemStack> itemStacks;
    // @formatter:on

    // CraftBukkit start
    @Unique
    private int maxStack = 64;

    @Unique
    private Location roastmc$location;

    public List<ItemStack> getContents() {
        return this.itemStacks;
    }

    public org.bukkit.inventory.InventoryHolder getOwner() {
        return null;
    }

    public void onOpen(CraftHumanEntity who) {
    }

    public void onClose(CraftHumanEntity who) {
    }

    public List<HumanEntity> getViewers() {
        return new ArrayList<>();
    }

    public int getMaxStackSize() {
        return maxStack;
    }

    public void setMaxStackSize(int size) {
        maxStack = size;
    }

    @Override
    public Location getLocation() {
        return roastmc$location;
    }

    @Override
    public void roastmc$setLocation(Location location) {
        this.roastmc$location = location;
    }
    // CraftBukkit end
}
