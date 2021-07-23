package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;

public class SakuraSapling extends RotatedPillarBlock {
    public SakuraSapling() {
        super(new SakuraTree(), PistonMovingBlockEntity.Properties
                .of(FluidState.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SnowyDirtBlock.GRASS));
    }
}
