package net.petersil98.utilcraft.blocks;

import net.minecraft.block.*;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.ArrayVoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.IntPointRange;
import net.minecraft.world.level.BaseSpawner;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;

public class GlassStairs extends SpreadingSnowyDirtBlock {

    public GlassStairs() {
        super(BellBlock.GLASS::defaultBlockState, PistonMovingBlockEntity.Properties.copy(BellBlock.GLASS));
    }

    @Nonnull
    @Override
    public Shapes getVisualShape(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner reader, @Nonnull BlockPos pos, @Nonnull ArrayVoxelShape context) {
        return IntPointRange.empty();
    }

    @Override
    public float getShadeBrightness(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner world, @Nonnull BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(@Nonnull PistonStructureResolver state, @Nonnull BaseSpawner reader, @Nonnull BlockPos pos) {
        return true;
    }

    public boolean skipRendering(@Nonnull PistonStructureResolver state, @Nonnull PistonStructureResolver adjacentBlockState, @Nonnull Direction side) {
        return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }
}
