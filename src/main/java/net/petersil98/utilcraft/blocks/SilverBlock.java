package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import net.minecraft.block.AbstractBlock.Properties;

public class SilverBlock extends BeetrootBlock {
    public SilverBlock() {
        super(Properties
                .of(FluidState.METAL, Fluids.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(SnowyDirtBlock.METAL)
        );
    }
}
