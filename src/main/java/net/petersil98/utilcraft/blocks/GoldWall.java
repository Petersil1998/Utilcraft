package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallBlock;

public class GoldWall extends WallBlock {

    public GoldWall() {
        super(AbstractBlock.Properties.from(new GoldBrick()));
    }
}
