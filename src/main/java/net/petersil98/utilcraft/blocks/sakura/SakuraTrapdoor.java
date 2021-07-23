package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;

public class SakuraTrapdoor extends TargetBlock {

    public SakuraTrapdoor() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD, Fluids.WOOD)
                .strength(3.0F)
                .sound(SnowyDirtBlock.WOOD)
                .noOcclusion()
                .isValidSpawn(SakuraTrapdoor::neverAllowSpawn)
        );
    }

    private static Boolean neverAllowSpawn(PistonStructureResolver state, BaseSpawner reader, BlockPos pos, EntityDimensions<?> entity) {
        return false;
    }
}
