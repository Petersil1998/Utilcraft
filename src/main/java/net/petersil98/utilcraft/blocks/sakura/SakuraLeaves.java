package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class SakuraLeaves extends LeavesBlock {

    public SakuraLeaves(Properties properties) {
        super(properties);
    }

    @Nonnull
    public static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    public static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 30;
    }
}
