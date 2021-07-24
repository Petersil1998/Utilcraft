package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class GoldWall extends WallBlock {

    public GoldWall() {
        super(BlockBehaviour.Properties.copy(new GoldBrick()));
    }
}
