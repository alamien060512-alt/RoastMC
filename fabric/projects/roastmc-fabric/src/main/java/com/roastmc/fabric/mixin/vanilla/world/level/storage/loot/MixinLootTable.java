package com.roastmc.fabric.mixin.vanilla.world.level.storage.loot;

import com.roastmc.fabric.injected.world.level.storage.loot.ContextLootTable;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(LootTable.class)
public class MixinLootTable implements ContextLootTable {
}
