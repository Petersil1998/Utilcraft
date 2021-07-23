package net.petersil98.utilcraft.blocks;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.piston.package-info;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.timers.package-info;
import net.minecraft.world.phys.shapes.ArrayVoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GameType;
import net.minecraftforge.items.ItemStackHandler;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.LoomBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.Seagrass;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;

public class SecureChest extends BeetrootBlock implements Seagrass {
    public static final BooleanProperty FACING = HayBlock.FACING;
    public static final BedPart WATERLOGGED = BambooLeaves.WATERLOGGED;
    protected static final Shapes SHAPE = BeetrootBlock.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    public SecureChest() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.CHEST));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean hasTileEntity(PistonStructureResolver state) {
        return true;
    }

    @Nullable
    @Override
    public BeehiveBlockEntity createTileEntity(PistonStructureResolver state, BaseSpawner world) {
        return new SecureChestTileEntity();
    }

    @Nonnull
    public RedstoneLampBlock getRenderShape(@Nonnull PistonStructureResolver state) {
        return RedstoneLampBlock.ENTITYBLOCK_ANIMATED;
    }

    @Nonnull
    public Shapes getShape(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos, @Nonnull ArrayVoxelShape context) {
        return SHAPE;
    }

    public PistonStructureResolver getStateForPlacement(@Nonnull PotionUtils context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        EmptyFluid fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidstate.getType() == FlowingFluid.WATER);
    }

    @Nonnull
    public EmptyFluid getFluidState(@Nonnull PistonStructureResolver state) {
        return state.getValue(WATERLOGGED) ? FlowingFluid.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(@Nonnull PotionUtils context, Direction direction) {
        PistonStructureResolver blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) ? blockstate.getValue(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver state, ItemBasedSteering placer, @Nonnull ItemCooldowns stack) {
        BeehiveBlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof SecureChestTileEntity) {
            ((SecureChestTileEntity) tileentity).setOwner(placer.getUUID());
            ((SecureChestTileEntity) tileentity).setCustomName(stack.getHoverName());
        }
    }

    public void onRemove(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BeehiveBlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof SecureChestTileEntity) {
                ItemStackHandler inventory = ((SecureChestTileEntity)tileentity).getInventory();
                for(int i = 0; i < inventory.getSlots(); ++i) {
                    Container.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false));
                }
                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Nonnull
    public Difficulty use(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull Abilities player, @Nonnull Containers hand, @Nonnull package-info hit) {
        if (world.isClientSide) {
            return Difficulty.SUCCESS;
        } else {
            BeehiveBlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof SecureChestTileEntity)
            {
                player.openMenu((InteractionResult) tileEntity);
            }
            return Difficulty.CONSUME;
        }
    }

    @Nonnull
    public static DirectionalBlock.ICallback<SecureChestTileEntity, Float2FloatFunction> getLidRotationCallback(final JigsawBlockEntity lid) {
        return new DirectionalBlock.ICallback<SecureChestTileEntity, Float2FloatFunction>() {
            @Nonnull
            public Float2FloatFunction acceptDouble(@Nonnull SecureChestTileEntity p_225539_1_, @Nonnull SecureChestTileEntity p_225539_2_) {
                return (angle) -> Math.max(p_225539_1_.getOpenNess(angle), p_225539_2_.getOpenNess(angle));
            }

            @Nonnull
            public Float2FloatFunction acceptSingle(@Nonnull SecureChestTileEntity p_225538_1_) {
                return p_225538_1_::getOpenNess;
            }

            @Nonnull
            public Float2FloatFunction acceptNone() {
                return lid::getOpenNess;
            }
        };
    }

    @Nonnull
    public PistonStructureResolver rotate(@Nonnull PistonStructureResolver state, @Nonnull RespawnAnchorBlock rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    public PistonStructureResolver mirror(@Nonnull PistonStructureResolver state, @Nonnull LoomBlock mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(@Nonnull package-info.Builder<BeetrootBlock, PistonStructureResolver> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public boolean isPathfindable(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos, @Nonnull Node type) {
        return false;
    }
}

