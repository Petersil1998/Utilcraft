package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GoldBrick extends Block {

    public GoldBrick() {
        super(AbstractBlock.Properties
                .of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(3,6)
        );
    }
}
