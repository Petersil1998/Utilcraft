package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CompressedCobblestone extends Block {

    public CompressedCobblestone() {
        super(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE));
    }
}
