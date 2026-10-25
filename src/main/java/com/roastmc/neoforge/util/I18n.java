package com.roastmc.neoforge.util;

import com.roastmc.neoforge.RoastMC;

/**
 * @author Mgazul
 * {@code @date} 2023/9/23 6:15:26
 */
public class I18n {

    public static String as(String key) {
        return RoastMC.i18n.as(key);
    }

    public static String as(String key, Object... objects) {
        return RoastMC.i18n.as(key, objects);
    }
}
