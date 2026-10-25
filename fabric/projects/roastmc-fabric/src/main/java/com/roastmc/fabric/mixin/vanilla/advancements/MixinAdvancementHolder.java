package com.roastmc.fabric.mixin.vanilla.advancements;

import com.roastmc.fabric.injected.advancements.ContextAdvancementHolder;
import net.minecraft.advancements.AdvancementHolder;
import org.bukkit.craftbukkit.advancement.CraftAdvancement;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(AdvancementHolder.class)
public abstract class MixinAdvancementHolder implements ContextAdvancementHolder {

    // CraftBukkit start
    @Override
    public org.bukkit.advancement.Advancement toBukkit() {
        return new CraftAdvancement((AdvancementHolder) (Object) this);
    }
    // CraftBukkit end
}
