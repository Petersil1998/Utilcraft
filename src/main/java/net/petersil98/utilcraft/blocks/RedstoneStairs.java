package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RedstoneStairs extends StairBlock {

    public RedstoneStairs() {
        super(() -> new GoldBrick().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK));
    }
}
