package net.petersil98.utilcraft.blocks;

import net.minecraft.block.WallBlock;

public class GoldWall extends WallBlock {

    public GoldWall() {
        super(Properties.copy(new GoldBrick()));
    }
}
