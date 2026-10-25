package com.roastmc.fabric.bukkit;

import java.util.Optional;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * @author Mgazul
 * @date 2026/3/28 18:36
 */
public class DoubleChestCombiner implements DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> {

    public Optional<MenuProvider> acceptDouble(final ChestBlockEntity first, final ChestBlockEntity second) {
        final CompoundContainer container = new CompoundContainer(first, second);

        return Optional.of(new Dummy.DoubleInventory(first, second, container));
    }

    public static class Dummy {

        public static class DoubleInventory implements MenuProvider {

            private final ChestBlockEntity first;
            private final ChestBlockEntity second;
            public final CompoundContainer container;

            public DoubleInventory(ChestBlockEntity first, ChestBlockEntity second, CompoundContainer container) {
                this.first = first;
                this.second = second;
                this.container = container;
            }
            // CraftBukkit end

            @Override
            public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
                if (first.canOpen(player) && second.canOpen(player)) {
                    first.unpackLootTable(inventory.player);
                    second.unpackLootTable(inventory.player);
                    return ChestMenu.sixRows(containerId, inventory, container);
                } else {
                    Direction connectedDirection = ChestBlock.getConnectedDirection(first.getBlockState());
                    Vec3 firstCenter = Vec3.atCenterOf(first.getBlockPos());
                    Vec3 centerBetweenChests = firstCenter.add(connectedDirection.getStepX() / 2.0, 0.0, connectedDirection.getStepZ() / 2.0);
                    BaseContainerBlockEntity.sendChestLockedNotifications(centerBetweenChests, player, this.getDisplayName());
                    return null;
                }
            }

            @Override
            public Component getDisplayName() {
                return (Component) (first.hasCustomName() ? first.getDisplayName() : (second.hasCustomName() ? second.getDisplayName() : Component.translatable("container.chestDouble")));
            }
        };
    }

    public Optional<MenuProvider> acceptSingle(ChestBlockEntity single) {
        return Optional.of(single);
    }

    @Override
    public Optional<MenuProvider> acceptNone() {
        return Optional.empty();
    }
};
