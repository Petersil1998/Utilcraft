package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

public class SakuraLog extends RepeaterBlock {

    public SakuraLog() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD, (blockState) -> blockState.getValue(RepeaterBlock.AXIS) == Direction.Axis.Y ? Fluids.COLOR_PINK : Fluids.WOOD)
                .strength(2.0F)
                .sound(SnowyDirtBlock.WOOD)
        );
    }

    @Override
    public int getFlammability(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 5;
    }
}
