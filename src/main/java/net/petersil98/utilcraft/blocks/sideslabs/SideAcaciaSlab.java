package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SideAcaciaSlab extends SideSlabBlock{
    public SideAcaciaSlab() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, MaterialColor.ADOBE)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }
}
