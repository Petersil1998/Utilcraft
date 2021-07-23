package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;

public class SakuraPressurePlate extends PotatoBlock {

    public SakuraPressurePlate() {
        super(PotatoBlock.Sensitivity.EVERYTHING,
                PistonMovingBlockEntity.Properties
                        .of(FluidState.WOOD, new SakuraPlanks().defaultMaterialColor())
                        .noCollission()
                        .strength(0.5F)
                        .sound(SnowyDirtBlock.WOOD)
        );
    }
}
