package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.material.FluidState;

public class RoseQuartzOre extends NoteBlock {
    public RoseQuartzOre() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .harvestLevel(4)
        );
    }
}
