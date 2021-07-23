package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BellBlock;

public class SideStoneSlab extends SideSlabBlock{

    public SideStoneSlab() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.STONE));
    }
}
