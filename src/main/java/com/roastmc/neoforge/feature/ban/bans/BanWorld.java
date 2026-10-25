package com.roastmc.neoforge.feature.ban.bans;

import com.mohistmc.tools.ListUtils;
import com.roastmc.neoforge.RoastMCConfig;
import com.roastmc.neoforge.feature.ban.BanConfig;
import com.roastmc.neoforge.feature.ban.BanType;
import com.roastmc.neoforge.feature.ban.ClickType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.resources.Identifier;
import org.bukkit.entity.Player;

/**
 * @author Mgazul
 * {@code @date} 2026/2/10 22:32
 */
public class BanWorld {

    public static Set<Identifier> CACHE = new HashSet<>();

    public static void addBan(Player player, String key) {
        List<String> old = BanConfig.getListByType(BanType.WORLD);
        ListUtils.isDuplicate(old, key);
        BanConfig.saveToYaml(player, ClickType.ADD, old, BanType.WORLD);
    }

    public static boolean checkBan(Identifier resourceLocation) {
        if (!RoastMCConfig.yml.getBoolean("bans.world", false)) return false;
        CACHE.add(resourceLocation);
        var list = BanConfig.getListByType(BanType.WORLD);
        if (list.isEmpty()) return false;
        return list.contains(resourceLocation.toString());
    }
}
