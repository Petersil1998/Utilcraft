package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.SlabBlock;

public class GoldSlab extends SlabBlock {

    public GoldSlab() {
        super(Properties.copy(new GoldBrick()));
    }
}
