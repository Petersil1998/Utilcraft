package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.petersil98.utilcraft.container.EntropyTableContainer;

import javax.annotation.Nonnull;

public class EntropyTable extends Block {

    public EntropyTable(){
        super(AbstractBlock.Properties.from(Blocks.CRAFTING_TABLE));
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(world, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public INamedContainerProvider getContainer(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new EntropyTableContainer(id, inventory, IWorldPosCallable.of(world, pos)), new StringTextComponent("Sushi Maker"));
    }
}
