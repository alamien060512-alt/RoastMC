package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextTransientCraftingContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(TransientCraftingContainer.class)
public abstract class MixinTransientCraftingContainer implements ContextTransientCraftingContainer {

    // @formatter:off
    @Shadow @Final private NonNullList<ItemStack> items;
    @Shadow @Final private AbstractContainerMenu menu;
    // @formatter:on

    // CraftBukkit start - add fields
    @Unique
    public List<HumanEntity> transaction = new ArrayList<>();

    @Unique
    public Container resultInventory;

    @Unique
    private Player owner;

    @Unique
    private int maxStack = 99;

    @Unique
    private RecipeHolder<?> currentRecipe;
    // CraftBukkit end

    @Override
    public InventoryType getInvType() {
        return items.size() == 4 ? InventoryType.CRAFTING : InventoryType.WORKBENCH;
    }

    @Override
    public void roastmc$setResultInventory(Container resultInventory) {
        this.resultInventory = resultInventory;
    }

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public List<ItemStack> getContents() {
        return this.items;
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

    public org.bukkit.inventory.InventoryHolder getOwner() {
        return (owner == null) ? null : owner.getBukkitEntity();
    }

    public int getMaxStackSize() {
        return maxStack;
    }

    public void setMaxStackSize(int size) {
        maxStack = size;
        if (resultInventory != null) {
            resultInventory.setMaxStackSize(size);
        }
    }

    public Location getLocation() {
        if (menu instanceof CraftingMenu) {
            return ((CraftingMenu) menu).access.getLocation();
        }
        return (owner == null) ? null : owner.getBukkitEntity().getLocation();
    }

    public RecipeHolder<?> getCurrentRecipe() {
        return currentRecipe;
    }

    public void setCurrentRecipe(RecipeHolder<?> currentRecipe) {
        this.currentRecipe = currentRecipe;
    }
}
