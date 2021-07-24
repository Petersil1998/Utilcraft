package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SakuraSapling extends SaplingBlock {
    public SakuraSapling() {
        super(new SakuraTree(), BlockBehaviour.Properties
                .of(Material.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS));
    }
}
