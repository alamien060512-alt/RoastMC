package com.roastmc.fabric.mixin.vanilla.world.level.portal;

import com.roastmc.fabric.injected.world.level.portal.ContextTeleportTransition;
import net.minecraft.world.level.portal.TeleportTransition;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * CraftBukkit - TeleportTransition carries a PlayerTeleportEvent.TeleportCause.
 * 对应 CB 给 record 增加 cause 组件; mixin 侧以伴生字段实现（record 结构不变）。
 *
 * @author Mgazul
 * @date 2026/10/24
 */
@Mixin(TeleportTransition.class)
public class MixinTeleportTransition implements ContextTeleportTransition {

    @Unique
    private PlayerTeleportEvent.TeleportCause roastmc$cause = PlayerTeleportEvent.TeleportCause.UNKNOWN;

    @Override
    public PlayerTeleportEvent.TeleportCause roastmc$cause() {
        return this.roastmc$cause;
    }

    @Override
    public void roastmc$cause(PlayerTeleportEvent.TeleportCause cause) {
        if (cause != null) {
            this.roastmc$cause = cause;
        }
    }
}
