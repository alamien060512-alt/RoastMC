package com.roastmc.fabric.injected.world.level.storage.loot;

import com.roastmc.fabric.util.ContextStateException;
import net.minecraft.world.Container;
import net.minecraft.world.level.storage.loot.LootParams;
import org.bukkit.craftbukkit.CraftLootTable;

public interface ContextLootTable {

    default void fillInventory(Container iinventory, LootParams lootparams, long i, boolean plugin) {
        throw new ContextStateException("Not implemented");
    }

    default CraftLootTable roastmc$craftLootTable() {
        throw new ContextStateException("Not implemented");
    }

    default void roastmc$craftLootTable(CraftLootTable craftLootTable) {
        throw new ContextStateException("Not implemented");
    }
}
