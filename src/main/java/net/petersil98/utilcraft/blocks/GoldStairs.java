package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;

public class GoldStairs extends SpreadingSnowyDirtBlock {

    public GoldStairs() {
        super(() -> new GoldBrick().defaultBlockState(), PistonMovingBlockEntity.Properties.copy(new GoldBrick()));
    }
}
