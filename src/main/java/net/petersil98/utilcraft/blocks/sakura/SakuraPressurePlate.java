package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SakuraPressurePlate extends PressurePlateBlock {

    public SakuraPressurePlate() {
        super(Sensitivity.EVERYTHING,
                Properties
                        .of(Material.WOOD, new SakuraPlanks().defaultMaterialColor())
                        .noCollission()
                        .strength(0.5F)
                        .sound(SoundType.WOOD)
        );
    }
}
