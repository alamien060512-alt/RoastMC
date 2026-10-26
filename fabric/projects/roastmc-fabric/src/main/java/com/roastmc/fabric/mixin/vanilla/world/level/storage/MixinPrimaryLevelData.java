package com.roastmc.fabric.mixin.vanilla.world.level.storage;

import com.roastmc.fabric.injected.world.level.storage.ContextPrimaryLevelData;
import net.minecraft.core.Registry;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/4/2 18:26
 */
@Mixin(PrimaryLevelData.class)
public class MixinPrimaryLevelData implements ContextPrimaryLevelData {

    @Shadow
    public LevelSettings settings;

    // CraftBukkit start - Add world and pdc
    public Registry<LevelStem> customDimensions;
    private ServerLevel world;
    protected Tag pdc;

    @Override
    public void setWorld(ServerLevel world) {
        if (this.world != null) {
            return;
        }
        this.world = world;
        world.getWorld().readBukkitValues(pdc);
        pdc = null;
    }
    // CraftBukkit end

    @Override
    public Registry<LevelStem> roastmc_customDimensions() {
        return customDimensions;
    }

    @Override
    public void roastmc_customDimensions(Registry<LevelStem> customDimensions) {
        this.customDimensions = customDimensions;
    }

    @Override
    public void checkName(String name) {
        if (!this.settings.levelName.equals(name)) {
            this.settings.levelName = name;
        }
    }
}
