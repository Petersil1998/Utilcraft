package net.petersil98.utilcraft.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class RedstoneStairs extends StairsBlock {

    public RedstoneStairs() {
        super(Blocks.REDSTONE_BLOCK::defaultBlockState, Properties.copy(Blocks.REDSTONE_BLOCK));
    }

    @Override
    public boolean isSignalSource(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos blockPos, @Nonnull Direction direction) {
        return 10;
    }
}
