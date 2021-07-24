package net.petersil98.utilcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.petersil98.utilcraft.container.SushiMakerContainer;

import javax.annotation.Nonnull;

public class SushiMaker extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public SushiMaker(){
        super(Properties.copy(Blocks.CRAFTING_TABLE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public INamedContainerProvider getMenuProvider(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new SushiMakerContainer(id, inventory, IWorldPosCallable.create(world, pos)), new StringTextComponent("Sushi Maker"));
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
