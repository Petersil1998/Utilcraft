package net.petersil98.utilcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class RedstoneStairs extends StairBlock {

    public RedstoneStairs(Properties properties) {
        super(Blocks.REDSTONE_BLOCK::defaultBlockState, properties);
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
