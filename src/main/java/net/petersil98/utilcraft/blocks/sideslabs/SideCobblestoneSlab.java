package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BellBlock;

public class SideCobblestoneSlab extends SideSlabBlock{
    public SideCobblestoneSlab() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.COBBLESTONE));
    }
}
