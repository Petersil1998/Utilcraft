package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class RoseQuartzBlock extends Block {
    public RoseQuartzBlock() {
        super(Properties
                .of(Material.METAL, MaterialColor.COLOR_PINK)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        );
    }
}
