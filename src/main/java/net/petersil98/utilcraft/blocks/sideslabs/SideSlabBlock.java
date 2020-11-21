package net.petersil98.utilcraft.blocks.sideslabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.petersil98.utilcraft.blocks.custom.ModBlockStateProperties;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;

public class SideSlabBlock extends Block implements IWaterLoggable {
    public static final EnumProperty<SideSlabType> TYPE = ModBlockStateProperties.SIDE_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape EAST_SHAPE = Block.makeCuboidShape(16.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape WEST_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 16.0D, 16.0D, 16.0D, 8.0D);

    public SideSlabBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(TYPE, SideSlabType.NORTH).with(WATERLOGGED, Boolean.FALSE));
    }

    public boolean isTransparent(BlockState state) {
        return state.get(TYPE) != SideSlabType.DOUBLE;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        SideSlabType sideSlabType = state.get(TYPE);
        switch(sideSlabType) {
            case DOUBLE:
                return VoxelShapes.fullCube();
            case EAST:
                return EAST_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return NORTH_SHAPE;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        BlockState blockstate = context.getWorld().getBlockState(blockpos);
        if (blockstate.getBlock() == this) {
            return blockstate.with(TYPE, SideSlabType.DOUBLE).with(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidstate = context.getWorld().getFluidState(blockpos);
            BlockState blockstate1 = this.getDefaultState().with(TYPE, SideSlabType.NORTH).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
            Direction direction = context.getPlacementHorizontalFacing();
            Vector3d vector = context.getHitVec();
            double diffX = vector.x - (double)blockpos.getX();
            double diffZ = vector.z - (double)blockpos.getZ();
            switch (direction){
                case EAST:
                case WEST:
                    return !(diffX > 0.5D) ? blockstate1.with(TYPE, SideSlabType.WEST) : blockstate1.with(TYPE, SideSlabType.EAST);
                case SOUTH:
                default:
                    return !(diffZ > 0.5D) ? blockstate1.with(TYPE, SideSlabType.NORTH) : blockstate1.with(TYPE, SideSlabType.SOUTH);
            }
        }
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        ItemStack itemstack = useContext.getItem();
        SideSlabType sideSlabType = state.get(TYPE);
        if (sideSlabType != SideSlabType.DOUBLE && itemstack.getItem() == this.asItem()) {
            if (useContext.replacingClickedOnBlock()) {
                if (useContext.getFace() == Direction.NORTH && sideSlabType == SideSlabType.SOUTH)
                    return true;
                if (useContext.getFace() == Direction.EAST && sideSlabType == SideSlabType.WEST)
                    return true;
                if (useContext.getFace() == Direction.SOUTH && sideSlabType == SideSlabType.NORTH)
                    return true;
                if (useContext.getFace() == Direction.WEST && sideSlabType == SideSlabType.EAST)
                    return true;
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public boolean receiveFluid(@Nonnull IWorld worldIn, @Nonnull BlockPos pos, BlockState state, @Nonnull FluidState fluidStateIn) {
        return state.get(TYPE) != SideSlabType.DOUBLE && IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
    }

    public boolean canContainFluid(@Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, BlockState state, @Nonnull Fluid fluidIn) {
        return state.get(TYPE) != SideSlabType.DOUBLE && IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld worldIn, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, PathType type) {
        switch(type) {
            case WATER:
                return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
            default:
                return false;
        }
    }
}