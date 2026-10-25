package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractArrow;

public class RoastMCModsAbstractArrow extends CraftAbstractArrow {

    public RoastMCModsAbstractArrow(CraftServer server, AbstractArrow entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsAbstractArrow{" + getType() + '}';
    }
}
