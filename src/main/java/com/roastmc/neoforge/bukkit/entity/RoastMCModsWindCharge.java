package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractWindCharge;

public class RoastMCModsWindCharge extends CraftAbstractWindCharge {

    public RoastMCModsWindCharge(CraftServer server, AbstractWindCharge entity) {
        super(server, entity);
    }
}
