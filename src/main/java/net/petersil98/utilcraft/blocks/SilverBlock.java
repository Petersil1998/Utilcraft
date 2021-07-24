package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SilverBlock extends Block {
    public SilverBlock() {
        super(Properties
                .of(Material.METAL, MaterialColor.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .sound(SoundType.METAL)
        );
    }
}
