package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class RoseQuartzBlock extends Block {
    public RoseQuartzBlock() {
        super(AbstractBlock.Properties
                .of(Material.METAL, MaterialColor.COLOR_PINK)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        );
    }
}
