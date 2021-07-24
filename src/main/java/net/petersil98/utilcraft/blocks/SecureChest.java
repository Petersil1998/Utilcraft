package net.petersil98.utilcraft.blocks;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.petersil98.utilcraft.block_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SecureChest extends Block implements SimpleWaterloggedBlock, EntityBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    public SecureChest() {
        super(BlockBehaviour.Properties.copy(Blocks.CHEST));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Nonnull
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
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
    private Direction getDirectionToAttach(@Nonnull BlockPlaceContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) ? blockstate.getValue(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, @Nonnull ItemStack stack) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof SecureChestTileEntity) {
            ((SecureChestTileEntity) tileentity).setOwner(placer.getUUID());
            ((SecureChestTileEntity) tileentity).setCustomName(stack.getHoverName());
        }
    }

    public void onRemove(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof SecureChestTileEntity) {
                ItemStackHandler inventory = ((SecureChestTileEntity)tileentity).getInventory();
                for(int i = 0; i < inventory.getSlots(); ++i) {
                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false));
                }
                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Nonnull
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof SecureChestTileEntity)
            {
                player.openMenu((MenuProvider) tileEntity);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nonnull
    public static DoubleBlockCombiner.Combiner<SecureChestTileEntity, Float2FloatFunction> getLidRotationCallback(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
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

    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SecureChestTileEntity(blockPos, blockState);
    }
}

