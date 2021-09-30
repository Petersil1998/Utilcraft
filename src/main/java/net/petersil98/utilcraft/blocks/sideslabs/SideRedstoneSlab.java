package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class SideRedstoneSlab extends SideSlabBlock {

    public SideRedstoneSlab(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSignalSource(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos blockPos, @Nonnull Direction direction) {
        return shouldEmitSignal(state, direction) ? 10 : 0;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
        return shouldEmitSignal(state, direction);
    }

    private boolean shouldEmitSignal(BlockState state, Direction direction){
        Direction facing = state.getValue(SideSlabBlock.TYPE).getFacingDirection();
        return facing != null && facing.getOpposite().equals(direction);
    }
}
