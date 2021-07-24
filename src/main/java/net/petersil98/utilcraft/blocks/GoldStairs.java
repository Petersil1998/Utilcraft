package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;

public class GoldStairs extends StairBlock {

    public GoldStairs() {
        super(() -> new GoldBrick().defaultBlockState(), BlockBehaviour.Properties.copy(new GoldBrick()));
    }
}
