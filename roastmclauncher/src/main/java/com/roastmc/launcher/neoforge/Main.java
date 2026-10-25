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

package com.roastmc.launcher.neoforge;

import com.mohistmc.i18n.i18n;
import com.roastmc.launcher.neoforge.action.Action;
import com.roastmc.launcher.neoforge.config.RoastMCConfigUtil;
import com.roastmc.launcher.neoforge.feature.DefaultLibraries;
import com.roastmc.launcher.neoforge.util.DataParser;
import com.roastmc.launcher.neoforge.util.I18n;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

public class Main {
    public static final boolean DEBUG = Boolean.getBoolean("roastmc.debug");
    public static final List<String> mainArgs = new ArrayList<>();
    public static String MCVERSION;
    public static i18n i18n;
    private static final String LOGO = """
         ____                 _   __  __  ____
        |  _ \\ ___   __ _ ___| |_|  \\/  |/ ___|
        | |_) / _ \\ / _` / __| __| |\\/| | |
        |  _ < (_) | (_| \\__ \\ |_| |  | | |___
        |_| \\_\\___/ \\__,_|___/\\__|_|  |_|\\____|
        """;

    public static String getVersion() {
        return (Main.class.getPackage().getImplementationVersion() != null) ? Main.class.getPackage().getImplementationVersion() : MCVERSION;
    }

    @SneakyThrows
    static void main(String[] args) {
        System.setProperty("org.jline.terminal.disableDeprecatedProviderWarning", "true");
        mainArgs.addAll(List.of(args));
        DataParser.parseVersions();
        DataParser.parseLaunchArgs();
        RoastMCConfigUtil.init();
        RoastMCConfigUtil.i18n();
        if (RoastMCConfigUtil.aBoolean("roastmc.show_logo", true)) {
            System.out.printf("%n%s%n%s - %s, Java(%s) %s PID: %s%n",
                LOGO,
                i18n.as("roastmc.launch.welcomemessage"),
                getVersion(),
                System.getProperty("java.class.version"),
                System.getProperty("java.version"),
                ManagementFactory.getRuntimeMXBean().getName().split("@")[0]
            );
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                                                      |");
            System.out.println("| Patreon: https://www.patreon.com/c/mohistmc          |");
            System.out.println("| 爱发电: https://ifdian.net/a/MohistMC                |");
            System.out.println("|                                                      |");
            System.out.println("+------------------------------------------------------+");
        }

        long startTime = System.currentTimeMillis();
        if (System.getProperty("log4j2.configurationFile") == null) {
            System.setProperty("log4j2.configurationFile", "log4j2_roastmc.xml");
        }
        System.out.println(" ");
        System.out.println(I18n.as("deployment.environment"));
        if (RoastMCConfigUtil.CHECK_LIBRARIES()) {
            DefaultLibraries.run();
        }
        var action = new Action();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double seconds = duration / 1000.0;
        System.out.println(I18n.as("deployment.finished", seconds));
        action.start();
    }
}
