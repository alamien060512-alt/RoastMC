package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftMob;

public class RoastMCModsMob extends CraftMob {

    public RoastMCModsMob(CraftServer server, Mob entity) {
        super(server, entity);
    }
}
