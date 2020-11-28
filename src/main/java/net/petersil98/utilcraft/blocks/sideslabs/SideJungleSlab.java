package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SideJungleSlab extends SideSlabBlock{
    public SideJungleSlab() {
        super(AbstractBlock.Properties.from(Blocks.JUNGLE_WOOD));
    }
}
