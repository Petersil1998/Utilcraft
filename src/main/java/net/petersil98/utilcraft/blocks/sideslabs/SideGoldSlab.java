package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.petersil98.utilcraft.blocks.GoldBrick;

public class SideGoldSlab extends SideSlabBlock{
    public SideGoldSlab() {
        super(AbstractBlock.Properties.from(new GoldBrick()));
    }
}
