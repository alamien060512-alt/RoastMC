package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftMinecart;


public class RoastMCModsMinecart extends CraftMinecart {

    public RoastMCModsMinecart(CraftServer server, AbstractMinecart entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsMinecart{" + getType() + '}';
    }
}
