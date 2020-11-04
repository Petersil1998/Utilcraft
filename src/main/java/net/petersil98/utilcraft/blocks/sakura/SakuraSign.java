package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class SakuraSign extends StandingSignBlock {

    public SakuraSign() {
        super(AbstractBlock.Properties
                .create(Material.WOOD)
                .doesNotBlockMovement()
                .hardnessAndResistance(1.0F)
                .sound(SoundType.WOOD),
                WoodType.OAK);
    }
}
