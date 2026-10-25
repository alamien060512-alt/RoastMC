package com.roastmc.fabric.mixin.vanilla.world.entity.vehicle.minecart;

import com.roastmc.fabric.injected.world.entity.vehicle.minecart.ContextMinecartTNT;
import net.minecraft.world.entity.vehicle.minecart.MinecartTNT;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/5/1 18:22
 */
@Mixin(MinecartTNT.class)
public class MixinMinecartTNT implements ContextMinecartTNT {

    public boolean isIncendiary = false; // CraftBukkit - add field

    public boolean roastmc$isIncendiary() {
        return isIncendiary;
    }

    public void roastmc$isIncendiary(boolean isIncendiary) {
        this.isIncendiary = isIncendiary;
    }
}
