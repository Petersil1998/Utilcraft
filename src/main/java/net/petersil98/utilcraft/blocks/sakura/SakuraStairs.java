package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SakuraStairs extends StairsBlock {

    public SakuraStairs(){
        super(() -> new SakuraPlanks().defaultBlockState(), Properties.copy(new SakuraPlanks()));
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }
}
