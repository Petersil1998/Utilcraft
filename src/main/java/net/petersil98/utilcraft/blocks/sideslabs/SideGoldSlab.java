package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.petersil98.utilcraft.blocks.GoldBrick;

public class SideGoldSlab extends SideSlabBlock{
    public SideGoldSlab() {
        super(AbstractBlock.Properties.copy(new GoldBrick()));
    }
}
