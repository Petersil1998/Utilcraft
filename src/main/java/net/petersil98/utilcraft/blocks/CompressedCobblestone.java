package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;

public class CompressedCobblestone extends BeetrootBlock {

    public CompressedCobblestone() {
        super(PistonMovingBlockEntity.Properties.copy(BellBlock.COBBLESTONE));
    }
}
