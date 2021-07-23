package net.petersil98.utilcraft.blocks;

import net.minecraft.block.*;

import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;

public class RedstoneStairs extends SpreadingSnowyDirtBlock {

    public RedstoneStairs() {
        super(() -> new GoldBrick().defaultBlockState(), PistonMovingBlockEntity.Properties.copy(BellBlock.REDSTONE_BLOCK));
    }
}
