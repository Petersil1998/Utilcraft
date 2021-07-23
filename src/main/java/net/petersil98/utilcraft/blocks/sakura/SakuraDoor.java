package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;

public class SakuraDoor extends DiodeBlock {

    public SakuraDoor() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.WOOD, new SakuraPlanks().defaultMaterialColor())
                .strength(3.0F)
                .sound(SnowyDirtBlock.WOOD)
                .noOcclusion());
    }
}
