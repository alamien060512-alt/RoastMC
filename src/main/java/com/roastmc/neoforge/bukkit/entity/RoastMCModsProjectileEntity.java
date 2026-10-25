package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftProjectile;

public class RoastMCModsProjectileEntity extends CraftProjectile {

    public RoastMCModsProjectileEntity(CraftServer server, Projectile entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsProjectileEntity{" + getType() + '}';
    }
}

