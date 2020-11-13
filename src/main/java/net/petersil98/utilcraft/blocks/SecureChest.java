package net.petersil98.utilcraft.blocks;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.petersil98.utilcraft.tile_entities.ModTileEntities;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class SecureChest extends AbstractChestBlock<SecureChestTileEntity> implements IWaterLoggable {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
    protected static final VoxelShape SHAPE_SINGLE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    private static final TileEntityMerger.ICallback<SecureChestTileEntity, Optional<IInventory>> INVENTORY_MERGER = new TileEntityMerger.ICallback<SecureChestTileEntity, Optional<IInventory>>() {
        public Optional<IInventory> func_225539_a_(@Nonnull SecureChestTileEntity p_225539_1_, @Nonnull SecureChestTileEntity p_225539_2_) {
            return Optional.of(new DoubleSidedInventory(p_225539_1_, p_225539_2_));
        }

        public Optional<IInventory> func_225538_a_(@Nonnull SecureChestTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        @Nonnull
        public Optional<IInventory> func_225537_b_() {
            return Optional.empty();
        }
    };
    private static final TileEntityMerger.ICallback<SecureChestTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<SecureChestTileEntity, Optional<INamedContainerProvider>>() {
        public Optional<INamedContainerProvider> func_225539_a_(@Nonnull final SecureChestTileEntity p_225539_1_,@Nonnull  final SecureChestTileEntity p_225539_2_) {
            final IInventory iinventory = new DoubleSidedInventory(p_225539_1_, p_225539_2_);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                public Container createMenu(int p_createMenu_1_, @Nonnull PlayerInventory p_createMenu_2_,@Nonnull  PlayerEntity p_createMenu_3_) {
                    if (p_225539_1_.canOpen(p_createMenu_3_) && p_225539_2_.canOpen(p_createMenu_3_)) {
                        p_225539_1_.fillWithLoot(p_createMenu_2_.player);
                        p_225539_2_.fillWithLoot(p_createMenu_2_.player);
                        return ChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, iinventory);
                    } else {
                        return null;
                    }
                }

                @Nonnull
                public ITextComponent getDisplayName() {
                    if (p_225539_1_.hasCustomName()) {
                        return p_225539_1_.getDisplayName();
                    } else {
                        return p_225539_2_.hasCustomName() ? p_225539_2_.getDisplayName() : new TranslationTextComponent("container.chestDouble");
                    }
                }
            });
        }

        public Optional<INamedContainerProvider> func_225538_a_(@Nonnull SecureChestTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        @Nonnull
        public Optional<INamedContainerProvider> func_225537_b_() {
            return Optional.empty();
        }
    };

    public SecureChest() {
        super(AbstractBlock.Properties
                .create(Material.WOOD)
                .hardnessAndResistance(2.5F)
                .sound(SoundType.WOOD),
                () -> ModTileEntities.SECURE_CHEST);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, ChestType.SINGLE).with(WATERLOGGED, Boolean.FALSE));
    }

    public static TileEntityMerger.Type getChestMergerType(BlockState state) {
        ChestType chesttype = state.get(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return TileEntityMerger.Type.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
        }
    }

    @Nonnull
    public BlockRenderType getRenderType(@Nonnull BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Nonnull
    public BlockState updatePostPlacement(BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld worldIn, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        if (facingState.isIn(this) && facing.getAxis().isHorizontal()) {
            ChestType chesttype = facingState.get(TYPE);
            if (stateIn.get(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && stateIn.get(FACING) == facingState.get(FACING) && getDirectionToAttached(facingState) == facing.getOpposite()) {
                return stateIn.with(TYPE, chesttype.opposite());
            }
        } else if (getDirectionToAttached(stateIn) == facing) {
            return stateIn.with(TYPE, ChestType.SINGLE);
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Nonnull
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        if (state.get(TYPE) == ChestType.SINGLE) {
            return SHAPE_SINGLE;
        } else {
            switch (getDirectionToAttached(state)) {
                case NORTH:
                default:
                    return SHAPE_NORTH;
                case SOUTH:
                    return SHAPE_SOUTH;
                case WEST:
                    return SHAPE_WEST;
                case EAST:
                    return SHAPE_EAST;
            }
        }
    }

    /**
     * Returns a facing pointing from the given state to its attached double chest
     */
    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(TYPE) == ChestType.LEFT ? direction.rotateY() : direction.rotateYCCW();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getPlacementHorizontalFacing().getOpposite();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = context.hasSecondaryUseForPlayer();
        Direction direction1 = context.getFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.rotateYCCW() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            if (direction == this.getDirectionToAttach(context, direction.rotateY())) {
                chesttype = ChestType.LEFT;
            } else if (direction == this.getDirectionToAttach(context, direction.rotateYCCW())) {
                chesttype = ChestType.RIGHT;
            }
        }

        return this.getDefaultState().with(FACING, direction).with(TYPE, chesttype).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    @Nonnull
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(BlockItemUseContext context, Direction direction) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction));
        return blockstate.isIn(this) && blockstate.get(TYPE) == ChestType.SINGLE ? blockstate.get(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof SecureChestTileEntity) {
                ((ChestTileEntity) tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }

    public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
            if (inamedcontainerprovider != null) {
                player.openContainer(inamedcontainerprovider);
                player.addStat(this.getOpenStat());
                PiglinTasks.func_234478_a_(player, true);
            }

            return ActionResultType.CONSUME;
        }
    }

    protected Stat<ResourceLocation> getOpenStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    @Nullable
    public static IInventory getChestInventory(SecureChest chest, BlockState state, World world, BlockPos pos, boolean override) {
        return chest.combineSecure(state, world, pos, override).apply(INVENTORY_MERGER).orElse(null);
    }

    @Nonnull
    public TileEntityMerger.ICallbackWrapper<? extends SecureChestTileEntity> combineSecure(@Nonnull BlockState state, @Nonnull  World world, @Nonnull  BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) {
            bipredicate = (worldIn, posIn) -> false;
        } else {
            bipredicate = SecureChest::isBlocked;
        }

        return TileEntityMerger.func_226924_a_(this.tileEntityType.get(), SecureChest::getChestMergerType, SecureChest::getDirectionToAttached, FACING, state, world, pos, bipredicate);
    }

    @Nonnull
    @Override
    public TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> combine(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, boolean override) {
        return null;
    }

    @Nullable
    public INamedContainerProvider getContainer(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos) {
        return this.combineSecure(state, worldIn, pos, false).apply(CONTAINER_MERGER).orElse(null);
    }

    @OnlyIn(Dist.CLIENT)
    public static TileEntityMerger.ICallback<SecureChestTileEntity, Float2FloatFunction> getLidRotationCallback(final IChestLid lid) {
        return new TileEntityMerger.ICallback<SecureChestTileEntity, Float2FloatFunction>() {
            @Nonnull
            public Float2FloatFunction func_225539_a_(@Nonnull SecureChestTileEntity p_225539_1_, @Nonnull SecureChestTileEntity p_225539_2_) {
                return (angle) -> Math.max(p_225539_1_.getLidAngle(angle), p_225539_2_.getLidAngle(angle));
            }

            @Nonnull
            public Float2FloatFunction func_225538_a_(@Nonnull SecureChestTileEntity p_225538_1_) {
                return p_225538_1_::getLidAngle;
            }

            @Nonnull
            public Float2FloatFunction func_225537_b_() {
                return lid::getLidAngle;
            }
        };
    }

    public TileEntity createNewTileEntity(@Nonnull IBlockReader worldIn) {
        return new SecureChestTileEntity();
    }

    public static boolean isBlocked(IWorld world, BlockPos pos) {
        return isBelowSolidBlock(world, pos) || isCatSittingOn(world, pos);
    }

    private static boolean isBelowSolidBlock(IBlockReader reader, BlockPos worldIn) {
        BlockPos blockpos = worldIn.up();
        return reader.getBlockState(blockpos).isNormalCube(reader, blockpos);
    }

    private static boolean isCatSittingOn(IWorld world, BlockPos pos) {
        List<CatEntity> list = world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
        if (!list.isEmpty()) {
            for (CatEntity catentity : list) {
                if (catentity.isEntitySleeping()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasComparatorInputOverride(@Nonnull BlockState state) {
        return true;
    }

    public int getComparatorInputOverride(@Nonnull BlockState blockState, @Nonnull World worldIn, @Nonnull BlockPos pos) {
        return Container.calcRedstoneFromInventory(getChestInventory(this, blockState, worldIn, pos, false));
    }

    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull PathType type) {
        return false;
    }
}
