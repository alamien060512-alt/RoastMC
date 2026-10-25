package com.roastmc.fabric.mixin.vanilla.world.level.levelgen.structure.templatesystem;

import com.roastmc.fabric.injected.world.level.levelgen.structure.templatesystem.ContextStructureTemplate;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataTypeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Mgazul
 * @date 2026/4/2 17:35
 */
@Mixin(StructureTemplate.class)
public class MixinStructureTemplate implements ContextStructureTemplate {

    // CraftBukkit start - data containers
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    public CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
    // CraftBukkit end

    @Override
    public CraftPersistentDataContainer roastmc_persistentDataContainer() {
        return persistentDataContainer;
    }

    @Inject(method = "save", at = @At("TAIL"))
    private void roastmc_bukkitSave(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        // CraftBukkit start - PDC
        if (!this.persistentDataContainer.isEmpty()) {
            tag.put("BukkitValues", this.persistentDataContainer.toTagCompound());
        }
        // CraftBukkit end
    }

    @Inject(method = "load", at = @At("TAIL"))
    private void roastmc_bukkitLoad(HolderGetter<Block> blockLookup, CompoundTag tag, CallbackInfo ci) {
        // CraftBukkit start - PDC
        Tag base = tag.get("BukkitValues");
        if (base instanceof CompoundTag) {
            this.persistentDataContainer.putAll((CompoundTag) base);
        }
        // CraftBukkit end
    }
}
