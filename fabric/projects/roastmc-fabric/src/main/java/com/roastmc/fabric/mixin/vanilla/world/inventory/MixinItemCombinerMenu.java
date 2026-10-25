package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextAbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCombinerMenu.class)
public abstract class MixinItemCombinerMenu implements ContextAbstractContainerMenu {

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void roastmc$stillValid(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!roastmc$checkReachable()) {
            cir.setReturnValue(true);
        }
    }
}
