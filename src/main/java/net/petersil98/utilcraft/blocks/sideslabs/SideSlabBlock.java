package net.petersil98.utilcraft.blocks.sideslabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.petersil98.utilcraft.blocks.custom.UtilcraftBlockStateProperties;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;
import org.jetbrains.annotations.NotNull;

public class SideSlabBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<SideSlabType> TYPE = UtilcraftBlockStateProperties.SIDE_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final String NAME = "side_slab";
    protected static final VoxelShape EAST_SHAPE = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);

    public SideSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE;
    }

    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        SideSlabType sideSlabType = state.getValue(TYPE);
        return switch (sideSlabType) {
            case DOUBLE -> Shapes.block();
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(@Nonnull BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.getBlock() == this) {
            return blockstate.setValue(TYPE, SideSlabType.DOUBLE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(blockpos);
            BlockState blockstate1 = this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            Direction direction = context.getHorizontalDirection();
            Vec3 vector = context.getClickLocation();
            double diffX = vector.x - (double)blockpos.getX();
            double diffZ = vector.z - (double)blockpos.getZ();
            return switch (direction) {
                case EAST, WEST -> !(diffX > 0.5D) ? blockstate1.setValue(TYPE, SideSlabType.WEST) : blockstate1.setValue(TYPE, SideSlabType.EAST);
                default -> !(diffZ > 0.5D) ? blockstate1.setValue(TYPE, SideSlabType.NORTH) : blockstate1.setValue(TYPE, SideSlabType.SOUTH);
            };
        }
    }

    public boolean canBeReplaced(@Nonnull BlockState state, @Nonnull BlockPlaceContext useContext) {
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

    public boolean placeLiquid(@Nonnull LevelAccessor world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull FluidState fluidState) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidState);
    }

    public boolean canPlaceLiquid(@Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Fluid fluid) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && SimpleWaterloggedBlock.super.canPlaceLiquid(world, pos, state, fluid);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Nonnull
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor world, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        if (type == PathComputationType.WATER) {
            return world.getFluidState(pos).is(FluidTags.WATER);
        }
        return false;
    }
}