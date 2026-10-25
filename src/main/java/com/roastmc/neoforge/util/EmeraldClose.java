package com.roastmc.neoforge.util;

import com.roastmc.neoforge.RoastMCConfig;
import java.util.Collection;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.item.Items;

public class EmeraldClose {

    public static void init(LivingEntity livingEntity, Collection<ItemEntity> items) {
        if (!RoastMCConfig.custom_raid_no_emerald) return;
        if (livingEntity instanceof Vindicator || livingEntity instanceof Evoker) {
            items.removeIf(item -> item.getItem().getItem() == Items.EMERALD);
        }
    }
}
