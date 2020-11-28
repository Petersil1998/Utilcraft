package net.petersil98.utilcraft.blocks;

import net.minecraft.block.*;

public class RedstoneStairs extends StairsBlock {

    public RedstoneStairs() {
        super(() -> new GoldBrick().getDefaultState(), AbstractBlock.Properties.from(Blocks.REDSTONE_BLOCK));
    }
}
