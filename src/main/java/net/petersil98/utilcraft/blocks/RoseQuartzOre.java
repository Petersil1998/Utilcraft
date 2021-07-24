package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;

public class RoseQuartzOre extends OreBlock {
    public RoseQuartzOre() {
        super(BlockBehaviour.Properties
                .of(Material.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)
                .harvestLevel(4)
        );
    }
}
