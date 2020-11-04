package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SakuraDoor extends DoorBlock {

    public SakuraDoor() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, new SakuraPlanks().getMaterialColor())
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD)
                .notSolid());
    }
}
