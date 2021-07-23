package net.petersil98.utilcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import net.minecraft.block.AbstractBlock.Properties;

public class SilverBlock extends Block {
    public SilverBlock() {
        super(Properties
                .of(Material.METAL, MaterialColor.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(SoundType.METAL)
        );
    }
}
