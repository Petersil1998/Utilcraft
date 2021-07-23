package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

public class SideSakuraSlab extends SideSlabBlock {

    public SideSakuraSlab() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD, Fluids.WOOD)
                .strength(2.0F, 3.0F)
                .sound(SnowyDirtBlock.WOOD)
        );
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