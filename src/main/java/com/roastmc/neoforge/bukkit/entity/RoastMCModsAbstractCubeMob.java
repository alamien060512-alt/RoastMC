package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractCubeMob;

public class RoastMCModsAbstractCubeMob extends CraftAbstractCubeMob {

    public RoastMCModsAbstractCubeMob(CraftServer server, AbstractCubeMob entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsAbstractCubeMob{" + getType() + '}';
    }
}
