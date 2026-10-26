package com.roastmc.neoforge.feature.ban.bans;

import com.roastmc.neoforge.RoastMCConfig;
import com.roastmc.neoforge.feature.ban.BanConfig;
import com.roastmc.neoforge.feature.ban.BanType;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

/**
 * @author Mgazul by MohistMC
 */
public class BanEffect {

    public static boolean check(String effectKey) {
        if (!RoastMCConfig.ban_effect_enable) return false;
        var list = BanConfig.getListByType(BanType.EFFECT);
        if (list.isEmpty()) return false;
        return list.contains(effectKey);
    }

    public static boolean check(Holder<MobEffect> effect) {
        return check(effect.getRegisteredName());
    }
}
