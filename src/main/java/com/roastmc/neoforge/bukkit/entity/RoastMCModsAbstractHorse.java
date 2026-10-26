package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractHorse;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.NotNull;

public class RoastMCModsAbstractHorse extends CraftAbstractHorse {

    public RoastMCModsAbstractHorse(CraftServer server, AbstractHorse entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "RoastMCModsAbstractHorse{" + getType() + '}';
    }

    @Override
    public @NotNull Horse.Variant getVariant() {
        return Horse.Variant.HORSE;
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return EntityCategory.NONE;
    }
}
