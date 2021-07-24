package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SakuraDoor extends DoorBlock {

    public SakuraDoor() {
        super(Properties
                .of(Material.WOOD, new SakuraPlanks().defaultMaterialColor())
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }
}
