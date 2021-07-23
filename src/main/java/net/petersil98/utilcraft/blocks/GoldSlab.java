package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.SignBlock;

public class GoldSlab extends SignBlock {

    public GoldSlab() {
        super(PistonMovingBlockEntity.Properties.copy(new GoldBrick()));
    }
}
