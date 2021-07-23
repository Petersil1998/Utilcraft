package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

public class SakuraStairs extends SpreadingSnowyDirtBlock {

    public SakuraStairs(){
        super(() -> new SakuraPlanks().defaultBlockState(), PistonMovingBlockEntity.Properties.copy(new SakuraPlanks()));
    }

    @Override
    public int getFlammability(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 5;
    }
}
