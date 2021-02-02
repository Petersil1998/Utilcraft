package net.petersil98.utilcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SilverBlock extends Block {
    public SilverBlock() {
        super(Properties
                .create(Material.IRON, MaterialColor.WHITE_TERRACOTTA)
                .setRequiresTool()
                .hardnessAndResistance(3.0F, 3.0F)
                .sound(SoundType.METAL)
        );
    }
}
