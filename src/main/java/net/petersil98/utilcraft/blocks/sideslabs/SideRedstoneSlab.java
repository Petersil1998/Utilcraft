package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class SideRedstoneSlab extends SideSlabBlock {

    public SideRedstoneSlab() {
        super(Properties.copy(Blocks.REDSTONE_BLOCK));
    }

    @Override
    public boolean isSignalSource(@Nonnull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos blockPos, @Nonnull Direction direction) {
        Direction facing = state.getValue(SideSlabBlock.TYPE).getFacingDirection();
        if(facing != null && facing.getOpposite().equals(direction)) {
            return 10;
        }
        return 0;
    }
}
