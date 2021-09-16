package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.StairBlock;

public class GoldStairs extends StairBlock {

    public GoldStairs(Properties properties) {
        super(() -> UtilcraftBlocks.GOLD_BRICK.get().defaultBlockState(), properties);
    }
}
