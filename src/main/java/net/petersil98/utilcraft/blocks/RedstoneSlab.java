package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.SignBlock;

public class RedstoneSlab extends SignBlock {

    public RedstoneSlab() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.REDSTONE_BLOCK));
    }
}
