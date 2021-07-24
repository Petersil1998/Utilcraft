package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

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
