package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftChestBoat;

public class RoastMCModsChestBoat extends CraftChestBoat {

    public RoastMCModsChestBoat(CraftServer server, AbstractChestBoat entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsChestBoat{" + getType() + '}';
    }
}
