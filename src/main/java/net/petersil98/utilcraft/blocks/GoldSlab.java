package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class GoldSlab extends SlabBlock {

    public GoldSlab() {
        super(BlockBehaviour.Properties.copy(new GoldBrick()));
    }
}
