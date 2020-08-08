package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SideBirchSlab extends SideSlabBlock {
    public SideBirchSlab() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, MaterialColor.SAND)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }
}
