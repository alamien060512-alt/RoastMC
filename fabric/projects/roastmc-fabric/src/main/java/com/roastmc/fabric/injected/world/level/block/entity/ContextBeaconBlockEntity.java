package com.roastmc.fabric.injected.world.level.block.entity;

import org.bukkit.potion.PotionEffect;

public interface ContextBeaconBlockEntity {

    default PotionEffect getPrimaryEffect() {
        return null;
    }

    default PotionEffect getSecondaryEffect() {
        return null;
    }
}
