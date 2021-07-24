package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.petersil98.utilcraft.block_entities.UtilcraftSignBlockEntity;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class SakuraSign extends StandingSignBlock implements EntityBlock {

    public SakuraSign() {
        super(Properties
                .of(Material.WOOD)
                .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD),
                WoodType.OAK);
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UtilcraftSignBlockEntity(blockPos, blockState);
    }
}
