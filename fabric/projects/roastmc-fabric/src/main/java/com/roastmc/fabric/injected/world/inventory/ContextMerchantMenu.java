package com.roastmc.fabric.injected.world.inventory;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.craftbukkit.inventory.view.CraftMerchantView;

/**
 * @author Mgazul
 * @date 2026/5/13 17:35
 */
public interface ContextMerchantMenu {

    default CraftMerchantView getBukkitView() {
        throw new ContextStateException("Not implemented");
    }
}
