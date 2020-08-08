package net.petersil98.utilcraft.blocks.sideslabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SideCobblestoneSlab extends SideSlabBlock{
    public SideCobblestoneSlab() {
        super(AbstractBlock.Properties.
                create(Material.ROCK, MaterialColor.STONE)
                .setRequiresTool()
                .hardnessAndResistance(2.0F, 6.0F)
        );
    }
}
