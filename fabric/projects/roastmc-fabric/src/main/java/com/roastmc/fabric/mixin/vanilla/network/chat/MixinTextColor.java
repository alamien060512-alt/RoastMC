package com.roastmc.fabric.mixin.vanilla.network.chat;

import com.roastmc.fabric.injected.network.chat.ContextTextColor;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(TextColor.class)
public class MixinTextColor implements ContextTextColor {

    @Shadow
    public ChatFormatting format;

    @Override
    public ChatFormatting roastmc$format() {
        return format;
    }

    @Override
    public void roastmc$setFormat(ChatFormatting format) {
        // Backed by a final field in the nms-patch; setting is handled at construction time
    }
}
