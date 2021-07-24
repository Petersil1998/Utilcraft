package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.petersil98.utilcraft.blocks.GoldBrick;

public class SideGoldSlab extends SideSlabBlock{
    public SideGoldSlab() {
        super(BlockBehaviour.Properties.copy(new GoldBrick()));
    }
}
