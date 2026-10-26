package com.roastmc.fabric.mixin.vanilla.network.chat;

import com.roastmc.fabric.injected.network.chat.ContextComponent;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Mgazul
 * @date 2026/5/13 21:19
 */
@Mixin(Component.class)
public interface MixinComponent extends ContextComponent {

    // @formatter:off
    @Shadow List<Component> getSiblings();
    // @formatter:on

    // CraftBukkit start
    default Stream<Component> stream() {
        return com.google.common.collect.Streams.concat(new Stream[]{Stream.of(this), this.getSiblings().stream().flatMap(Component::stream)});
    }

    @Override
    default Iterator<Component> iterator() {
        return this.stream().iterator();
    }
    // CraftBukkit end
}
