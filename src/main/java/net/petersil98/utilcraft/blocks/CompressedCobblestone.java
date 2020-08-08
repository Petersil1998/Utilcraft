package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CompressedCobblestone extends Block {

    public CompressedCobblestone() {
        super(AbstractBlock.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(2,6)
        );
    }
}
