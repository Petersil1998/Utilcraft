package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BellBlock;

public class SideRedstoneSlab extends SideSlabBlock {

    public SideRedstoneSlab() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.REDSTONE_BLOCK));
    }
}
