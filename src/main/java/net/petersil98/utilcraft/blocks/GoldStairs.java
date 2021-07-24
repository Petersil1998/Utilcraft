package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.StairBlock;

public class GoldStairs extends StairBlock {

    public GoldStairs() {
        super(() -> new GoldBrick().defaultBlockState(), Properties.copy(new GoldBrick()));
    }
}
