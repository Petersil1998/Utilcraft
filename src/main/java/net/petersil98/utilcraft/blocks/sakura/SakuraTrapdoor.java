package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SakuraTrapdoor extends TrapDoorBlock {

    public SakuraTrapdoor(Properties properties) {
        super(properties);
    }

    public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
