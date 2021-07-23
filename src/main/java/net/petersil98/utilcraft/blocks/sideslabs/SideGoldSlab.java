package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.petersil98.utilcraft.blocks.GoldBrick;

public class SideGoldSlab extends SideSlabBlock{
    public SideGoldSlab() {
        super(PistonMovingBlockEntity.Properties.copy(new GoldBrick()));
    }
}
