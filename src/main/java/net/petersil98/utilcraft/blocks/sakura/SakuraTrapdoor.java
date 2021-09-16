package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SakuraTrapdoor extends TrapDoorBlock {

    public SakuraTrapdoor(Properties properties) {
        super(properties);
    }

    public static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
