package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class SakuraWallSign extends WallSignBlock {

    public SakuraWallSign() {
        super(AbstractBlock.Properties
                .create(Material.WOOD)
                .doesNotBlockMovement()
                .hardnessAndResistance(1.0F)
                .sound(SoundType.WOOD)
                //.lootFrom(new SakuraSign())
                , WoodType.OAK)
        ;
    }
}
