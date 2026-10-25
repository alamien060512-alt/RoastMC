package com.roastmc.neoforge.feature.ban.bans;

import com.roastmc.neoforge.RoastMCConfig;

/**
 * @author Mgazul by MohistMC
 * @date 2023/8/9 20:09:51
 */
public class BanEvents {

    public static boolean banFireTick() {
        return RoastMCConfig.doFireTick;
    }

    public static boolean banExplosion() {
        return RoastMCConfig.explosion;
    }

    public static boolean banFarmlandTrample() {
        return RoastMCConfig.farmlandTrample;
    }
}
