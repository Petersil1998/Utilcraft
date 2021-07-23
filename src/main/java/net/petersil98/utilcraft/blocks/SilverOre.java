package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class SilverOre extends NoteBlock {
    public SilverOre() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.STONE, Fluids.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
        );
    }
}
