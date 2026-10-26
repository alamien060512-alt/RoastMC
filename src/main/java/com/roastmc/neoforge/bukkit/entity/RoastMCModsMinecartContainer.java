package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecartContainer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftMinecartContainer;

public class RoastMCModsMinecartContainer extends CraftMinecartContainer {

    public RoastMCModsMinecartContainer(CraftServer server, AbstractMinecartContainer entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsMinecartContainer{" + getType() + '}';
    }
}
