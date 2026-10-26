package com.roastmc.fabric.mixin.vanilla.world.entity.item;

import com.roastmc.fabric.injected.world.entity.item.ContextPrimedTnt;
import net.minecraft.world.entity.item.PrimedTnt;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/5/1 18:31
 */
@Mixin(PrimedTnt.class)
public class MixinPrimedTnt implements ContextPrimedTnt {

    public boolean isIncendiary = false; // CraftBukkit - add field

    public boolean roastmc$isIncendiary() {
        return isIncendiary;
    }

    public void roastmc$isIncendiary(boolean isIncendiary) {
        this.isIncendiary = isIncendiary;
    }
}
