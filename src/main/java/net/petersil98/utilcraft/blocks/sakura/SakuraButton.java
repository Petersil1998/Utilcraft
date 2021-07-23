package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.WitherRoseBlock;
import net.minecraft.world.level.material.FluidState;

public class SakuraButton extends WitherRoseBlock {

    public SakuraButton() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.DECORATION)
                .noCollission()
                .strength(0.5F)
                .sound(SnowyDirtBlock.WOOD)
        );
    }
}
