package com.roastmc.fabric.mixin.vanilla.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/10/24
 */
@Mixin(NetherPortalBlock.class)
public class MixinNetherPortalBlock {

    // CraftBukkit start - EntityPortalEnterEvent
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    protected void roastmc$portalEnter(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (entity.canUsePortal(false)) {
            EntityPortalEnterEvent event = new EntityPortalEnterEvent(entity.getBukkitEntity(), new org.bukkit.Location(((com.roastmc.fabric.injected.world.level.ContextLevel) level).getWorld(), pos.getX(), pos.getY(), pos.getZ()));
            level.getCraftServer().getPluginManager().callEvent(event);
            // CraftBukkit: EntityPortalEnterEvent 为通知事件, 不可取消
        }
    }
    // CraftBukkit end
}
