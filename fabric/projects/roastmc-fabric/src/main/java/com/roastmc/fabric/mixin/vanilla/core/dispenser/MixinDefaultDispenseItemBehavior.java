package com.roastmc.fabric.mixin.vanilla.core.dispenser;

import com.roastmc.fabric.bukkit.DispenseEventHooks;
import com.roastmc.fabric.injected.core.dispenser.ContextDefaultDispenseItemBehavior;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DefaultDispenseItemBehavior.class)
public abstract class MixinDefaultDispenseItemBehavior implements ContextDefaultDispenseItemBehavior {

    // CraftBukkit start
    @Unique
    private boolean roastmc$dropper;

    @Override
    public void roastmc$dropper(boolean dropper) {
        this.roastmc$dropper = dropper;
    }

    @Override
    public boolean roastmc$dropper() {
        return this.roastmc$dropper;
    }
    // CraftBukkit end

    /**
     * @author Mgazul
     * @reason CraftBukkit - fire BlockDispenseEvent in spawnItem
     */
    @Overwrite
    protected ItemStack execute(BlockSource source, ItemStack dispensed) {
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        Position position = DispenserBlock.getDispensePosition(source);
        ItemStack itemStack = dispensed.split(1);

        // CraftBukkit start
        if (!DispenseEventHooks.spawnItem(source.level(), itemStack, 6, direction, source, this.roastmc$dropper)) {
            dispensed.grow(1);
        }
        // CraftBukkit end
        return dispensed;
    }
}
