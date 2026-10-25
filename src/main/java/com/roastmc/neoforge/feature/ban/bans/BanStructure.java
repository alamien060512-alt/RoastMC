package com.roastmc.neoforge.feature.ban.bans;

import com.roastmc.neoforge.RoastMCConfig;
import com.roastmc.neoforge.feature.ban.BanConfig;
import com.roastmc.neoforge.feature.ban.BanType;
import com.roastmc.neoforge.feature.ban.ClickType;
import com.roastmc.neoforge.feature.ban.BanSaveInventory;
import com.roastmc.neoforge.util.I18n;
import java.util.List;
import net.minecraft.resources.Identifier;
import org.bukkit.entity.Player;

/**
 * @author Mgazul
 * @date 2026/7/2 02:40
 */
public class BanStructure {

    public static void addBan(Player player, String key) {
        List<String> old = BanConfig.getListByType(BanType.STRUCTURE);
        if (old.contains(key)) {
            player.sendMessage(I18n.as("banscmd.add.structure.exists"));
            return;
        }
        old.add(key);
        BanSaveInventory banSaveInventory = new BanSaveInventory(BanType.STRUCTURE, I18n.as("banscmd.gui.add.structure"));
        banSaveInventory.saveToYaml(player, ClickType.ADD, old, BanType.STRUCTURE);
    }

    public static boolean checkBan(Identifier resourceLocation) {
        if (!RoastMCConfig.ban_structure_enable) return false;
        var list = BanConfig.getListByType(BanType.STRUCTURE);
        if (list.isEmpty()) return false;
        return list.contains(resourceLocation.toString()) || list.contains(resourceLocation.getNamespace() + ":*");
    }
}
