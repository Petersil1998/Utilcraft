package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SilverOre extends OreBlock {
    public SilverOre() {
        super(AbstractBlock.Properties
                .of(Material.STONE, MaterialColor.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
        );
    }
}
