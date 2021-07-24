package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public class SideRedstoneSlab extends SideSlabBlock {

    public SideRedstoneSlab() {
        super(BlockBehaviour.Properties.copy(Blocks.REDSTONE_BLOCK));
    }
}
