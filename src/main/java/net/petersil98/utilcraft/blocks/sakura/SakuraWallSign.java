package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.petersil98.utilcraft.block_entities.UtilcraftSignTileEntity;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class SakuraWallSign extends WallSignBlock implements EntityBlock {

    public SakuraWallSign(Block sign) {
        super(BlockBehaviour.Properties
                .of(Material.WOOD)
                .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .dropsLike(sign)
                , WoodType.OAK);
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UtilcraftSignTileEntity(blockPos, blockState);
    }
}
