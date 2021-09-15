package net.petersil98.utilcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class RedstoneSlab extends SlabBlock {

    public RedstoneSlab() {
        super(Properties.copy(Blocks.REDSTONE_BLOCK));
    }

    @Override
    public boolean isSignalSource(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos blockPos, @Nonnull Direction direction) {
        return 10;
    }
}
