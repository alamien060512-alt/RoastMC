package org.bukkit.craftbukkit.inventory;

import com.roastmc.fabric.bukkit.inventory.BukkitLecternInventory;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import org.bukkit.block.Lectern;
import org.bukkit.inventory.LecternInventory;

public class CraftInventoryLectern extends CraftInventory implements LecternInventory {

    public MenuProvider tile;

    public CraftInventoryLectern(Container inventory) {
        super(inventory);
        if (inventory instanceof BukkitLecternInventory bukkitLecternInventory) {
            this.tile = bukkitLecternInventory.getLectern();
        }
    }

    @Override
    public Lectern getHolder() {
        return (Lectern) inventory.getOwner();
    }
}
