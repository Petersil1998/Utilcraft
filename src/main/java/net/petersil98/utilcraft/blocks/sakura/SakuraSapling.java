package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SakuraSapling extends SaplingBlock {
    public SakuraSapling() {
        super(new SakuraTree(), AbstractBlock.Properties
                .of(Material.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS));
    }
}
