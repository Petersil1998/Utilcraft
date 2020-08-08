package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SideOakSlab extends SideSlabBlock{

    public SideOakSlab() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, MaterialColor.WOOD)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD));
    }
}
