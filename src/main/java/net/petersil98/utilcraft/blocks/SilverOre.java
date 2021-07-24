package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class SilverOre extends OreBlock {
    public SilverOre() {
        super(BlockBehaviour.Properties
                .of(Material.STONE, MaterialColor.TERRACOTTA_WHITE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
        );
    }
}
