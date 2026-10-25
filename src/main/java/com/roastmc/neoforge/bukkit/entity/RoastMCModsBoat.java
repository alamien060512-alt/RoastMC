package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftBoat;

public class RoastMCModsBoat extends CraftBoat {

    public RoastMCModsBoat(CraftServer server, AbstractBoat entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsBoat{" + getType() + '}';
    }
}
