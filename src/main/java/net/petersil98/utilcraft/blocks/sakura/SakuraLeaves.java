package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class SakuraLeaves extends LeavesBlock {
    public SakuraLeaves(Properties properties) {
        super(properties);
    }

    @Nonnull
    public static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 30;
    }
}
