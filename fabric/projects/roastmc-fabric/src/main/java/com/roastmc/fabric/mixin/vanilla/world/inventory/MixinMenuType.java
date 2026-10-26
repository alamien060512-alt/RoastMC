package com.roastmc.fabric.mixin.vanilla.world.inventory;

import com.roastmc.fabric.injected.world.inventory.ContextLecternMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.LecternMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MenuType.class)
public class MixinMenuType {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/MenuType;register(Ljava/lang/String;Lnet/minecraft/world/inventory/MenuType$MenuSupplier;)Lnet/minecraft/world/inventory/MenuType;"))
    private static <T extends net.minecraft.world.inventory.AbstractContainerMenu> MenuType<T> roastmc$register(String name, MenuType.MenuSupplier<T> constructor) {
        if ("lectern".equals(name)) {
            return Registry.register(BuiltInRegistries.MENU, name, (MenuType<T>) new MenuType<LecternMenu>((containerId, inventory) -> {
                LecternMenu menu = new LecternMenu(containerId);
                ((ContextLecternMenu) menu).roastmc$player(inventory);
                return menu;
            }, FeatureFlags.VANILLA_SET));
        }
        return Registry.register(BuiltInRegistries.MENU, name, new MenuType<>(constructor, FeatureFlags.VANILLA_SET));
    }
}
