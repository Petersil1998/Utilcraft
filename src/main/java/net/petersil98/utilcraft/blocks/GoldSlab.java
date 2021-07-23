package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlabBlock;

public class GoldSlab extends SlabBlock {

    public GoldSlab() {
        super(AbstractBlock.Properties.copy(new GoldBrick()));
    }
}
