package com.roastmc.neoforge.bukkit.entity;


import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;

public class RoastMCModsEntity extends CraftEntity {

    public RoastMCModsEntity(CraftServer server, net.minecraft.world.entity.Entity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsEntity{" + this.getType() + '}';
    }
}
