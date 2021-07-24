package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GoldBrick extends Block {

    public GoldBrick() {
        super(Properties
                .of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(3,6)
        );
    }
}
