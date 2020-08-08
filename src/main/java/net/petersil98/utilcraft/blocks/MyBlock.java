package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MyBlock extends Block {
    public MyBlock() {
        super(AbstractBlock.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(1,1200)
                .setLightLevel((p_235467_0_) -> {
                    return 15;
                })
        );
    }
}
