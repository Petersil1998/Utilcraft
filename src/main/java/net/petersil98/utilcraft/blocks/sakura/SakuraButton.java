package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;

public class SakuraButton extends WoodButtonBlock {

    public SakuraButton() {
        super(Properties
                .of(Material.DECORATION)
                .noCollission()
                .strength(0.5F)
                .sound(SoundType.WOOD)
        );
    }
}
