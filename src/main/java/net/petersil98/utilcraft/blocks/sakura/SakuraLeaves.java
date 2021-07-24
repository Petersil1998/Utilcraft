package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SakuraLeaves extends LeavesBlock {
    public SakuraLeaves() {
        super(BlockBehaviour.Properties
                .of(Material.LEAVES)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .isValidSpawn(SakuraLeaves::allowsSpawnOnLeaves)
                .isSuffocating(SakuraLeaves::isntSolid)
                .isViewBlocking(SakuraLeaves::isntSolid)
        );
    }

    @Nonnull
    private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
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
