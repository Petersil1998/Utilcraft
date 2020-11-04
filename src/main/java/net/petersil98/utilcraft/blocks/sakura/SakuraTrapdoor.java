package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SakuraTrapdoor extends TrapDoorBlock {

    public SakuraTrapdoor() {
        super(AbstractBlock.Properties
                .create(Material.WOOD, MaterialColor.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD)
                .notSolid()
                .setAllowsSpawn(SakuraTrapdoor::neverAllowSpawn)
        );
    }

    private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
