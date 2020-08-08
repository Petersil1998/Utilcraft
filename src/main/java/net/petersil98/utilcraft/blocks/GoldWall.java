package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;

public class GoldWall extends WallBlock {

    public GoldWall() {
        super(AbstractBlock.Properties
                .create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(3,6)
        );
    }
}
