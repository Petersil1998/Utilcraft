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
import net.petersil98.utilcraft.blocks.custom.UtilcraftBlockStateProperties;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;

public class SideSlabBlock extends Block implements IWaterLoggable {
    public static final EnumProperty<SideSlabType> TYPE = UtilcraftBlockStateProperties.SIDE_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final String NAME = "side_slab";
    protected static final VoxelShape EAST_SHAPE = Block.box(16.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 16.0D, 16.0D, 16.0D, 8.0D);

    public SideSlabBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE;
    }

    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        SideSlabType sideSlabType = state.getValue(TYPE);
        switch(sideSlabType) {
            case DOUBLE:
                return VoxelShapes.block();
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
    public BlockState getStateForPlacement(@Nonnull BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.getBlock() == this) {
            return blockstate.setValue(TYPE, SideSlabType.DOUBLE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(blockpos);
            BlockState blockstate1 = this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            Direction direction = context.getHorizontalDirection();
            Vector3d vector = context.getClickLocation();
            double diffX = vector.x - (double)blockpos.getX();
            double diffZ = vector.z - (double)blockpos.getZ();
            switch (direction){
                case EAST:
                case WEST:
                    return !(diffX > 0.5D) ? blockstate1.setValue(TYPE, SideSlabType.WEST) : blockstate1.setValue(TYPE, SideSlabType.EAST);
                case SOUTH:
                default:
                    return !(diffZ > 0.5D) ? blockstate1.setValue(TYPE, SideSlabType.NORTH) : blockstate1.setValue(TYPE, SideSlabType.SOUTH);
            }
        }
    }

    public boolean canBeReplaced(@Nonnull BlockState state, @Nonnull BlockItemUseContext useContext) {
        ItemStack itemstack = useContext.getItemInHand();
        SideSlabType sideSlabType = state.getValue(TYPE);
        if (sideSlabType != SideSlabType.DOUBLE && itemstack.getItem() == this.asItem()) {
            if (useContext.replacingClickedOnBlock()) {
                if (useContext.getClickedFace() == Direction.NORTH && sideSlabType == SideSlabType.SOUTH)
                    return true;
                if (useContext.getClickedFace() == Direction.EAST && sideSlabType == SideSlabType.WEST)
                    return true;
                if (useContext.getClickedFace() == Direction.SOUTH && sideSlabType == SideSlabType.NORTH)
                    return true;
                if (useContext.getClickedFace() == Direction.WEST && sideSlabType == SideSlabType.EAST)
                    return true;
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Nonnull
    public FluidState getFluidState(@Nonnull BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean placeLiquid(@Nonnull IWorld world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull FluidState fluidState) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && IWaterLoggable.super.placeLiquid(world, pos, state, fluidState);
    }

    public boolean canPlaceLiquid(@Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Fluid fluid) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && IWaterLoggable.super.canPlaceLiquid(world, pos, state, fluid);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Nonnull
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld world, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, PathType type) {
        if (type == PathType.WATER) {
            return world.getFluidState(pos).is(FluidTags.WATER);
        }
        return false;
    }
}