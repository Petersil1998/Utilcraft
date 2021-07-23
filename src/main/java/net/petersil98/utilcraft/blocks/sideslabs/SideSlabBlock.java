package net.petersil98.utilcraft.blocks.sideslabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.Seagrass;
import net.minecraft.world.level.lighting.package-info;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.piston.package-info;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.ArrayVoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.IntPointRange;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GrassColor;
import net.petersil98.utilcraft.blocks.custom.UtilcraftBlockStateProperties;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;

public class SideSlabBlock extends BeetrootBlock implements Seagrass {
    public static final DirectionProperty<SideSlabType> TYPE = UtilcraftBlockStateProperties.SIDE_SLAB_TYPE;
    public static final BedPart WATERLOGGED = BambooLeaves.WATERLOGGED;
    public static final String NAME = "side_slab";
    protected static final Shapes EAST_SHAPE = BeetrootBlock.box(16.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final Shapes NORTH_SHAPE = BeetrootBlock.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final Shapes WEST_SHAPE = BeetrootBlock.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final Shapes SOUTH_SHAPE = BeetrootBlock.box(0.0D, 0.0D, 16.0D, 16.0D, 16.0D, 8.0D);

    public SideSlabBlock(PistonMovingBlockEntity.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public boolean useShapeForLightOcclusion(@Nonnull PistonStructureResolver state) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE;
    }

    protected void createBlockStateDefinition(@Nonnull package-info.Builder<BeetrootBlock, PistonStructureResolver> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Nonnull
    public Shapes getShape(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos, @Nonnull ArrayVoxelShape context) {
        SideSlabType sideSlabType = state.getValue(TYPE);
        switch(sideSlabType) {
            case DOUBLE:
                return IntPointRange.block();
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
    public PistonStructureResolver getStateForPlacement(@Nonnull PotionUtils context) {
        BlockPos blockpos = context.getClickedPos();
        PistonStructureResolver blockstate = context.getLevel().getBlockState(blockpos);
        if (blockstate.getBlock() == this) {
            return blockstate.setValue(TYPE, SideSlabType.DOUBLE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            EmptyFluid fluidstate = context.getLevel().getFluidState(blockpos);
            PistonStructureResolver blockstate1 = this.defaultBlockState().setValue(TYPE, SideSlabType.NORTH).setValue(WATERLOGGED, fluidstate.getType() == FlowingFluid.WATER);
            Direction direction = context.getHorizontalDirection();
            EntityHitResult vector = context.getClickLocation();
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

    public boolean canBeReplaced(@Nonnull PistonStructureResolver state, @Nonnull PotionUtils useContext) {
        ItemCooldowns itemstack = useContext.getItemInHand();
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
    public EmptyFluid getFluidState(@Nonnull PistonStructureResolver state) {
        return state.getValue(WATERLOGGED) ? FlowingFluid.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean placeLiquid(@Nonnull GrassColor world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver state, @Nonnull EmptyFluid fluidState) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && Seagrass.super.placeLiquid(world, pos, state, fluidState);
    }

    public boolean canPlaceLiquid(@Nonnull BaseSpawner world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver state, @Nonnull package-info fluid) {
        return state.getValue(TYPE) != SideSlabType.DOUBLE && Seagrass.super.canPlaceLiquid(world, pos, state, fluid);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Nonnull
    public PistonStructureResolver updateShape(@Nonnull PistonStructureResolver state, @Nonnull Direction facing, @Nonnull PistonStructureResolver facingState, @Nonnull GrassColor world, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(currentPos, FlowingFluid.WATER, FlowingFluid.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    public boolean isPathfindable(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos, Node type) {
        if (type == Node.WATER) {
            return world.getFluidState(pos).is(BlockTags.WATER);
        }
        return false;
    }
}