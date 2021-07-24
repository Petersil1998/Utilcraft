package net.petersil98.utilcraft.blocks;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SecureChest extends Block implements IWaterLoggable {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    public SecureChest() {
        super(Properties.copy(Blocks.CHEST));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SecureChestTileEntity();
    }

    @Nonnull
    public BlockRenderType getRenderShape(@Nonnull BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }

    public BlockState getStateForPlacement(@Nonnull BlockItemUseContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Nonnull
    public FluidState getFluidState(@Nonnull BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(@Nonnull BlockItemUseContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) ? blockstate.getValue(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof SecureChestTileEntity) {
            ((SecureChestTileEntity) tileentity).setOwner(placer.getUUID());
            ((SecureChestTileEntity) tileentity).setCustomName(stack.getHoverName());
        }
    }

    public void onRemove(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof SecureChestTileEntity) {
                ItemStackHandler inventory = ((SecureChestTileEntity)tileentity).getInventory();
                for(int i = 0; i < inventory.getSlots(); ++i) {
                    InventoryHelper.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false));
                }
                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Nonnull
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof SecureChestTileEntity)
            {
                player.openMenu((INamedContainerProvider) tileEntity);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Nonnull
    public static TileEntityMerger.ICallback<SecureChestTileEntity, Float2FloatFunction> getLidRotationCallback(final IChestLid lid) {
        return new TileEntityMerger.ICallback<SecureChestTileEntity, Float2FloatFunction>() {
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
    public BlockState rotate(@Nonnull BlockState state, @Nonnull Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    public BlockState mirror(@Nonnull BlockState state, @Nonnull Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull PathType type) {
        return false;
    }
}

