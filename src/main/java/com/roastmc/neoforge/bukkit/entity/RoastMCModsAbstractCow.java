package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.animal.cow.AbstractCow;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractCow;

public class RoastMCModsAbstractCow extends CraftAbstractCow {

    public RoastMCModsAbstractCow(CraftServer server, AbstractCow entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsAbstractCow{" + getType() + '}';
    }
}
