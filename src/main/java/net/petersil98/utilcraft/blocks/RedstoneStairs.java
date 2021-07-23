package net.petersil98.utilcraft.blocks;

import net.minecraft.block.*;

public class RedstoneStairs extends StairsBlock {

    public RedstoneStairs() {
        super(() -> new GoldBrick().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.REDSTONE_BLOCK));
    }
}
