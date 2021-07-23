package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;

public class SakuraFence extends FaceAttachedHorizontalDirectionalBlock {

    public SakuraFence() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD, new SakuraPlanks().defaultMaterialColor())
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
