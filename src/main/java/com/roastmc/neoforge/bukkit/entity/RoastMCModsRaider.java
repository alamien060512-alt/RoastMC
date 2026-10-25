package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftRaider;
import org.bukkit.entity.EntityCategory;
import org.jetbrains.annotations.NotNull;

public class RoastMCModsRaider extends CraftRaider {

    public RoastMCModsRaider(CraftServer server, Raider entity) {
        super(server, entity);
    }

    @Override
    public Raider getHandle() {
        return (Raider) this.entity;
    }

    @Override
    public String toString() {
        return "RoastMCModsRaider{" + getType() + '}';
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return EntityCategory.ILLAGER;
    }
}
