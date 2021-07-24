package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class SakuraStairs extends StairBlock {

    public SakuraStairs(){
        super(() -> new SakuraPlanks().defaultBlockState(), BlockBehaviour.Properties.copy(new SakuraPlanks()));
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }
}
