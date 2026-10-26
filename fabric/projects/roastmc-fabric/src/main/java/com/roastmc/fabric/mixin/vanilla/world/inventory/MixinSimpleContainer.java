package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextLocationContainer;
import net.minecraft.world.SimpleContainer;
import org.bukkit.Location;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(SimpleContainer.class)
public class MixinSimpleContainer implements ContextLocationContainer {

    @Unique
    private Location roastmc$location;

    @Override
    public Location getLocation() {
        return roastmc$location;
    }

    @Override
    public void roastmc$setLocation(Location location) {
        this.roastmc$location = location;
    }
}
