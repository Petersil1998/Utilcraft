package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GoldSlab extends SlabBlock {

    public GoldSlab() {
        super(AbstractBlock.Properties
                .create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(3,6)
        );
    }
}
