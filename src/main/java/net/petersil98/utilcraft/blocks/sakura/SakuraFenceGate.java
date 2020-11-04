package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.petersil98.utilcraft.blocks.ModBlocks;

public class SakuraFenceGate extends FenceGateBlock {

    public SakuraFenceGate() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, new SakuraPlanks().getMaterialColor())
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD)
        );
    }
}
