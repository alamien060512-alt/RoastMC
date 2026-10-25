package com.roastmc.fabric.mixin.vanilla.server.players;

import com.roastmc.fabric.injected.server.players.ContextStoredUserList;
import net.minecraft.server.players.StoredUserList;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Mgazul
 * @date 2026/6/8
 */
@Mixin(StoredUserList.class)
public class MixinStoredUserList implements ContextStoredUserList {
}
