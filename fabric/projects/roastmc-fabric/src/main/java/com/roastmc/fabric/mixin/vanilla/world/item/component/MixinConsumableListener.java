package com.roastmc.fabric.mixin.vanilla.world.item.component;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ConsumableListener;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ConsumableListener.class)
public interface MixinConsumableListener {

    default void cancelUsingItem(ServerPlayer serverplayer, ItemStack itemstack) {
    }
}
