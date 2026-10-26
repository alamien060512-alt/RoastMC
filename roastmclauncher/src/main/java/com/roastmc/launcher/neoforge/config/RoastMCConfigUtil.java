/*
 * Copyright (C) MohistMC.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.roastmc.launcher.neoforge.config;

import com.mohistmc.i18n.i18n;
import com.roastmc.launcher.neoforge.Main;
import com.mohistmc.yaml.file.YamlConfiguration;
import java.io.File;
import java.util.List;
import java.util.Locale;

public class RoastMCConfigUtil {

    public static final File roastmc_yml = new File("config/roastmc", "roastmc.yml");
    public static final YamlConfiguration yml = YamlConfiguration.loadConfiguration(roastmc_yml);

    public static void init() {
        try {
            File parent = roastmc_yml.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!roastmc_yml.exists()) {
                roastmc_yml.createNewFile();
            }
        } catch (Exception ignored) {
        }
    }

    public static boolean NETWORKMANAGER_DEBUG() {
        String key = "networkmanager.debug";
        if (yml.get(key) == null) {
            yml.set(key, false);
            save();
        }
        return yml.getBoolean(key, false);
    }

    public static List<String> NETWORKMANAGER_INTERCEPT() {
        String key = "networkmanager.intercept";
        if (yml.get(key) == null) {
            yml.set(key, List.of());
            save();
        }
        return yml.getStringList(key);
    }

    public static boolean CHECK_LIBRARIES() {
        String key = "roastmc.libraries.check";
        if (yml.get(key) == null) {
            yml.set(key, true);
            save();
        }
        return yml.getBoolean(key, true);
    }

    public static boolean aBoolean(String key, boolean defaultReturn) {
        return yml.getBoolean(key, defaultReturn);
    }

    public static void i18n() {
        Main.i18n = new i18n(Main.class.getClassLoader(), ROASTMCLANG());
    }

    public static void save() {
        try {
            yml.save(roastmc_yml);
        } catch (Exception ignored) {
        }
    }

    public static boolean isCN() {
        return ROASTMCLANG().contains("CN");
    }

    public static String ROASTMCLANG() {
        String key = "roastmc.lang";
        if (yml.get(key) == null) {
            yml.set(key, Locale.getDefault().toString());
            save();
        }
        return yml.getString(key, Locale.getDefault().toString());
    }

    public static boolean AutoDeleteMods() {
        String key = "roastmc.auto_delete_mods";
        if (yml.get(key) == null) {
            yml.set(key, true);
            save();
        }
        return yml.getBoolean(key, true);
    }
}
