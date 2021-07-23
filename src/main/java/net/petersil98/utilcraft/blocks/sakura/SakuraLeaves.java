package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;

public class SakuraLeaves extends KelpPlantBlock {
    public SakuraLeaves() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.LEAVES)
                .strength(0.2F)
                .randomTicks()
                .sound(SnowyDirtBlock.GRASS)
                .noOcclusion()
                .isValidSpawn(SakuraLeaves::allowsSpawnOnLeaves)
                .isSuffocating(SakuraLeaves::isntSolid)
                .isViewBlocking(SakuraLeaves::isntSolid)
        );
    }

    @Nonnull
    private static Boolean allowsSpawnOnLeaves(PistonStructureResolver state, BaseSpawner reader, BlockPos pos, EntityDimensions<?> entity) {
        return entity == EntityDimensions.OCELOT || entity == EntityDimensions.PARROT;
    }

    private static boolean isntSolid(PistonStructureResolver state, BaseSpawner reader, BlockPos pos) {
        return false;
    }

    @Override
    public int getFlammability(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(PistonStructureResolver state, BaseSpawner world, BlockPos pos, Direction face) {
        return 30;
    }
}
