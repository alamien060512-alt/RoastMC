package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LecternMenu;

public interface ContextLecternMenu {

    default LecternMenu roastmc$player(Inventory playerInventory) {
        throw new ContextStateException("Not implemented");
    }
}
