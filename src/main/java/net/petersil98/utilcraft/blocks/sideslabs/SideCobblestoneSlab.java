package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public class SideCobblestoneSlab extends SideSlabBlock{
    public SideCobblestoneSlab() {
        super(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    }
}
