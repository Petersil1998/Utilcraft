package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;

public class RedstoneSlab extends SlabBlock {

    public RedstoneSlab() {
        super(BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK));
    }
}
