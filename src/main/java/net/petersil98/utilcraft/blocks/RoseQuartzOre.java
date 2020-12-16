package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;

public class RoseQuartzOre extends OreBlock {
    public RoseQuartzOre() {
        super(AbstractBlock.Properties
                .create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3.0F, 3.0F)
                .harvestLevel(4)
        );
    }
}
