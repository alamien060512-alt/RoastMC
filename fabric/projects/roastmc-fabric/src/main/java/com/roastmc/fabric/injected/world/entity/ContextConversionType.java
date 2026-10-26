package com.roastmc.fabric.injected.world.entity;

import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.Mob;

public interface ContextConversionType {

    default void postConvert(Mob mob, Mob mob1, ConversionParams conversionparams) {

    }
    // CraftBukkit

}
