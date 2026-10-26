package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.vehicle.VehicleEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftVehicle;

public class RoastMCModsVehicle extends CraftVehicle {

    public RoastMCModsVehicle(CraftServer server, VehicleEntity entity) {
        super(server, entity);
    }
}
