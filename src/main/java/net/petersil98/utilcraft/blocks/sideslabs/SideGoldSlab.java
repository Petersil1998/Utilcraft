package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SideGoldSlab extends SideSlabBlock{
    public SideGoldSlab() {
        super(AbstractBlock.Properties
                .create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(3,6)
        );
    }
}
