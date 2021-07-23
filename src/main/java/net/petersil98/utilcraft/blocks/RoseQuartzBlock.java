package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class RoseQuartzBlock extends BeetrootBlock {
    public RoseQuartzBlock() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.METAL, Fluids.COLOR_PINK)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SnowyDirtBlock.METAL)
        );
    }
}
