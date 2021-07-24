package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public class SideStoneSlab extends SideSlabBlock{

    public SideStoneSlab() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE));
    }
}
