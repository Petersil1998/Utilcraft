package net.petersil98.utilcraft.blocks;

import net.minecraft.block.StairsBlock;

public class GoldStairs extends StairsBlock {

    public GoldStairs(Properties properties) {
        super(() -> UtilcraftBlocks.GOLD_BRICK.get().defaultBlockState(), properties);
    }
}
