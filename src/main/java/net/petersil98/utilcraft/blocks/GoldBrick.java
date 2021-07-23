package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;

public class GoldBrick extends BeetrootBlock {

    public GoldBrick() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.METAL)
                .sound(SnowyDirtBlock.METAL)
                .strength(3,6)
        );
    }
}
