package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SakuraFence extends FenceBlock {

    public SakuraFence() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, new SakuraPlanks().getMaterialColor())
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }
}
