package com.roastmc.neoforge.bukkit.entity;

import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftThrownPotion;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class RoastMCModsThrownPotion extends CraftThrownPotion {

    public RoastMCModsThrownPotion(CraftServer server, AbstractThrownPotion entity) {
        super(server, entity);
    }

    @Override
    public PotionMeta getPotionMeta() {
        return (PotionMeta) CraftItemStack.getItemMeta(this.getHandle().getItem());
    }

    @Override
    public String toString() {
        return "RoastMCModsThrownPotion{" + getType() + '}';
    }
}
