package com.roastmc.neoforge.util;

/**
 * @author Mgazul
 * @date 2026/4/12 02:24
 */
public class RoastMCVersion {

    public static String getVersion() {
        return "${roastmc_version}";
    }

    public static String getPaperVersion() {
        return "${paper_version}";
    }

    public static String getPurpurVersion() {
        return "${purpur_version}";
    }
}
