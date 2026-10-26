package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.npc.villager.AbstractVillager;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractVillager;

public class RoastMCModsAbstractVillager extends CraftAbstractVillager {

    public RoastMCModsAbstractVillager(CraftServer server, AbstractVillager entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsAbstractVillager{" + getType() + '}';
    }
}
