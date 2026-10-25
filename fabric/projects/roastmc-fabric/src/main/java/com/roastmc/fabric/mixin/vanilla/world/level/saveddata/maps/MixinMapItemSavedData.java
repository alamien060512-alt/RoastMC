package com.roastmc.fabric.mixin.vanilla.world.level.saveddata.maps;

import com.roastmc.fabric.bukkit.BukkitUtils;
import com.roastmc.fabric.injected.world.level.saveddata.maps.ContextMapItemSavedData;
import java.util.UUID;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.map.CraftMapView;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/4/2 15:39
 */
@Mixin(MapItemSavedData.class)
public abstract class MixinMapItemSavedData implements ContextMapItemSavedData {

    @Shadow public ResourceKey<Level> dimension;
    // CraftBukkit start
    public CraftMapView mapView;
    public UUID uniqueId = null;
    public MapId id;

    @Inject(method = "<init>(IIBZZZLnet/minecraft/resources/ResourceKey;)V", at = @At("RETURN"))
    private void roastmc$initBukkit(int centerX, int centerZ, byte scale, boolean trackingPosition, boolean unlimitedTracking, boolean locked, ResourceKey dimension, CallbackInfo ci) {
        // CraftBukkit start
        updateUUID();
        this.mapView = new CraftMapView(((MapItemSavedData) (Object) this));
    }

    private static ResourceKey<Level> getWorldKey(ResourceKey<Level> resourcekey, long uuidLeast, long uuidMost) {
        Level lookup = BukkitUtils.getServer().getLevel(resourcekey);
        if (lookup != null) {
            return resourcekey;
        }

        if (uuidLeast != 0L && uuidMost != 0L) {
            UUID uniqueId = new UUID(uuidMost, uuidLeast);

            CraftWorld world = (CraftWorld) Bukkit.getWorld(uniqueId);
            // Check if the stored world details are correct.
            if (world == null) {
                /* All Maps which do not have their valid world loaded are set to a dimension which hopefully won't be reached.
                   This is to prevent them being corrupted with the wrong map data. */
                // PAIL: Use Vanilla exception handling for now
            } else {
                return world.getHandle().dimension();
            }
        }
        throw new IllegalArgumentException("Invalid map dimension: " + resourcekey);
    }

    @Nullable
    private UUID updateUUID() {
        if (this.uniqueId == null) {
            Level level = BukkitUtils.getServer().getLevel(this.dimension);
            if (level != null) {
                this.uniqueId = level.getWorld().getUID();
            }
        }

        return this.uniqueId;
    }
    // CraftBukkit end

    @Override
    public CraftMapView roastmc_mapView() {
      return  this.mapView;
    }

    @Override
    public UUID roastmc_uniqueId() {
       return this.updateUUID();
    }

    @Override
    public void roastmc_uniqueId(UUID uuid) {
      this.uniqueId = uuid;
    }

    @Override
    public MapId roastmc_id() {
      return this.id;
    }

    @Override
    public void roastmc_id(MapId id) {
       this.id = id;
    }
}
