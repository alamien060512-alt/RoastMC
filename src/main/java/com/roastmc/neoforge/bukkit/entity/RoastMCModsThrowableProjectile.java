package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftThrowableProjectile;

public class RoastMCModsThrowableProjectile extends CraftThrowableProjectile {

    public RoastMCModsThrowableProjectile(CraftServer server, ThrowableItemProjectile entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsThrowableProjectile{" + getType() + '}';
    }
}
