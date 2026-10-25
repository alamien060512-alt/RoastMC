package com.roastmc.fabric.mixin.vanilla.world.entity.vehicle.minecart;

import com.roastmc.fabric.injected.world.entity.vehicle.minecart.ContextAbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import org.bukkit.util.Vector;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/4/2 19:41
 */
@Mixin(AbstractMinecart.class)
public class MixinAbstractMinecart implements ContextAbstractMinecart  {

    // CraftBukkit start
    public boolean slowWhenEmpty = true;
    private double derailedX = 0.5;
    private double derailedY = 0.5;
    private double derailedZ = 0.5;
    private double flyingX = 0.95;
    private double flyingY = 0.95;
    private double flyingZ = 0.95;
    public Double maxSpeed;
    public double powRailAccelMult = 1.0;
    // CraftBukkit end

    @Override
    public boolean roastmc_slowWhenEmpty() {
        return slowWhenEmpty;
    }

    @Override
    public void roastmc_slowWhenEmpty(boolean slowWhenEmpty) {
        this.slowWhenEmpty = slowWhenEmpty;
    }

    @Override
    public double roastmc_maxSpeed() {
        return maxSpeed;
    }

    @Override
    public void roastmc_maxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void roastmc_powRailAccelMult(double powRailAccelMult) {
        this.powRailAccelMult = powRailAccelMult;
    }

    @Override
    public double roastmc_powRailAccelMult() {
        return powRailAccelMult;
    }

    // CraftBukkit start - Methods for getting and setting flying and derailed velocity modifiers
    @Override
    public Vector getFlyingVelocityMod() {
        return new Vector(flyingX, flyingY, flyingZ);
    }

    @Override
    public void setFlyingVelocityMod(Vector flying) {
        flyingX = flying.getX();
        flyingY = flying.getY();
        flyingZ = flying.getZ();
    }

    @Override
    public Vector getDerailedVelocityMod() {
        return new Vector(derailedX, derailedY, derailedZ);
    }

    @Override
    public void setDerailedVelocityMod(Vector derailed) {
        derailedX = derailed.getX();
        derailedY = derailed.getY();
        derailedZ = derailed.getZ();
    }
    // CraftBukkit end
}
