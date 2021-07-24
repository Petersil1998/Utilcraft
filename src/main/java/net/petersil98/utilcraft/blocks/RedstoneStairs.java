package net.petersil98.utilcraft.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;

public class RedstoneStairs extends StairsBlock {

    public RedstoneStairs() {
        super(() -> new GoldBrick().defaultBlockState(), Properties.copy(Blocks.REDSTONE_BLOCK));
    }
}
