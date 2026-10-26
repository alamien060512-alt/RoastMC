package com.roastmc.fabric.mixin.vanilla.advancements;

import net.minecraft.advancements.AdvancementTree;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(AdvancementTree.class)
public class MixinAdvancementTree {

    @Redirect(method = "addAll", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;)V"))
    private void roastmc_removeLog(Logger instance, String format, Object arg) {
        // CraftBukkit - moved to AdvancementDataWorld#reload
    }
}
