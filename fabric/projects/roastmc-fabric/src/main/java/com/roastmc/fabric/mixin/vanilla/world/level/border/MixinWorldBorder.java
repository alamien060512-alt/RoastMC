package com.roastmc.fabric.mixin.vanilla.world.level.border;

import com.roastmc.fabric.injected.world.level.border.ContextWorldBorder;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.world.level.border.WorldBorder;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mgazul
 * @date 2026/4/2 16:14
 */
@Mixin(WorldBorder.class)
public abstract class MixinWorldBorder implements ContextWorldBorder {

    // @formatter:off
    @Shadow public abstract void applyInitialSettings(long gameTime);
    @Shadow private boolean initialized;
    // @formatter:on

    @Unique
    private AtomicBoolean applyInitialSettings_force = new AtomicBoolean(false);

    @Inject(method = "applyInitialSettings(J)V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/border/WorldBorder;initialized:Z", ordinal = 0, opcode = Opcodes.GETFIELD), cancellable = true)
    private void roastmc_initialized(long gameTime, CallbackInfo ci) {
        if (applyInitialSettings_force.getAndSet(false)) {
            this.initialized = true;
            ci.cancel();
        }
    }

    @Override
    public void applyInitialSettings(long gameTime, boolean force) {
        applyInitialSettings_force.set(force);
        applyInitialSettings(gameTime);
    }
}
