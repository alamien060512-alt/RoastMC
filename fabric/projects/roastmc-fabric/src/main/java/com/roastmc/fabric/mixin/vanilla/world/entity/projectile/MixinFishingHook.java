package com.roastmc.fabric.mixin.vanilla.world.entity.projectile;

import com.roastmc.fabric.injected.world.entity.projectile.ContextFishingHook;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/5/1 17:24
 */
@Mixin(FishingHook.class)
public class MixinFishingHook implements ContextFishingHook {

    // CraftBukkit start - Extra variables to enable modification of fishing wait time, values are minecraft defaults
    public int minWaitTime = 100;
    public int maxWaitTime = 600;
    public int minLureTime = 20;
    public int maxLureTime = 80;
    public float minLureAngle = 0.0F;
    public float maxLureAngle = 360.0F;
    public boolean applyLure = true;
    public boolean rainInfluenced = true;
    public boolean skyInfluenced = true;
    // CraftBukkit end

    public int roastmc$minLureTime() {
        return minLureTime;
    }

    public void roastmc$minLureTime(int minLureTime) {
        this.minLureTime = minLureTime;
    }

    public int roastmc$maxLureTime() {
        return maxLureTime;
    }

    public void roastmc$maxLureTime(int maxLureTime) {
        this.maxLureTime = maxLureTime;
    }

    public float roastmc$minLureAngle() {
        return minLureAngle;
    }

    public void roastmc$minLureAngle(float minLureAngle) {
        this.minLureAngle = minLureAngle;
    }

    public float roastmc$maxLureAngle() {
        return maxLureAngle;
    }

    public void roastmc$maxLureAngle(float maxLureAngle) {
        this.maxLureAngle = maxLureAngle;
    }

    public boolean roastmc$rainInfluenced() {
        return rainInfluenced;
    }

    public void roastmc$rainInfluenced(boolean rainInfluenced) {
        this.rainInfluenced = rainInfluenced;
    }

    public boolean roastmc$skyInfluenced() {
        return skyInfluenced;
    }

    public void roastmc$skyInfluenced(boolean skyInfluenced) {
        this.skyInfluenced = skyInfluenced;
    }

    public int roastmc$minWaitTime() {
        return minWaitTime;
    }

    public void roastmc$minWaitTime(int minWaitTime) {
        this.minWaitTime = minWaitTime;
    }

    public int roastmc$maxWaitTime() {
        return maxWaitTime;
    }

    public void roastmc$maxWaitTime(int minWaitTime) {
        this.minWaitTime = minWaitTime;
    }

    public boolean roastmc$applyLure() {
        return applyLure;
    }

    public void roastmc$applyLure(boolean applyLure) {
        this.applyLure = applyLure;
    }
}
