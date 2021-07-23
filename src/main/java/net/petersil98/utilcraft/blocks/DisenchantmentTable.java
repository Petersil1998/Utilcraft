package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.timers.package-info;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GameType;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;
import net.petersil98.utilcraft.tile_entities.DisenchantmentTableTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DisenchantmentTable extends BeetrootBlock {
    public DisenchantmentTable() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.STONE, Fluids.COLOR_RED)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 1200.0F)
        );
    }

    @Override
    public boolean hasTileEntity(PistonStructureResolver state) {
        return true;
    }

    @Nullable
    @Override
    public BeehiveBlockEntity createTileEntity(PistonStructureResolver state, BaseSpawner world) {
        return new DisenchantmentTableTileEntity();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public Difficulty use(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull Abilities player, @Nonnull Containers hand, package-info hit) {
        if (world.isClientSide) {
            return Difficulty.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return Difficulty.CONSUME;
        }
    }


    @Nullable
    public InteractionResult getMenuProvider(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos) {
        BeehiveBlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof DisenchantmentTableTileEntity) {
            Component itextcomponent = ((InteractionResultHolder)tileentity).getDisplayName();
            return new Nameable((id, inventory, player) -> new DisenchantmentTableContainer(id, inventory, ChestMenu.create(world, pos)), itextcomponent);
        } else {
            return null;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver state, ItemBasedSteering placer, @Nonnull ItemCooldowns stack) {
        if (stack.hasCustomHoverName()) {
            BeehiveBlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof DaylightDetectorBlockEntity) {
                ((DaylightDetectorBlockEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }

    }

    public boolean isPathfindable(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos, @Nonnull Node type) {
        return false;
    }
}
