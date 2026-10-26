package com.roastmc.fabric.mixin.vanilla.world.item.trading;

import com.roastmc.fabric.injected.world.item.trading.ContextMerchant;
import net.minecraft.world.item.trading.Merchant;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(Merchant.class)
public interface MixinMerchant extends ContextMerchant {
}
