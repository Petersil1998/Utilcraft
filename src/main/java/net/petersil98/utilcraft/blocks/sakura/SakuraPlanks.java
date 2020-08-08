package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SakuraPlanks extends Block {

    public SakuraPlanks() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, MaterialColor.WOOD)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }
}
