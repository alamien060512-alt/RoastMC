package com.roastmc.fabric.injected.world.item.trading;

import com.roastmc.fabric.util.ContextStateException;
import org.bukkit.craftbukkit.inventory.CraftMerchant;

public interface ContextMerchant {

    default CraftMerchant getCraftMerchant() {
        throw new ContextStateException("Not implemented");
    }
}
