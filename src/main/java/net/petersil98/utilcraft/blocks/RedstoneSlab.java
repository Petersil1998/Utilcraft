package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;

public class RedstoneSlab extends SlabBlock {

    public RedstoneSlab() {
        super(AbstractBlock.Properties.from(Blocks.REDSTONE_BLOCK));
    }
}
