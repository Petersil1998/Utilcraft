package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class GoldStairs extends StairsBlock {

    public GoldStairs() {
        super(() -> new GoldBrick().getDefaultState(), AbstractBlock.Properties
                .create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(3,6)
        );
    }
}
