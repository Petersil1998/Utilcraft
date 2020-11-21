package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;

public class SakuraLog extends RotatedPillarBlock {

    public SakuraLog() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, (p_235431_2_) -> p_235431_2_.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.PINK : MaterialColor.WOOD)
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD)
        );
    }
}
