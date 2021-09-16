package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.petersil98.utilcraft.block_entities.UtilcraftSignBlockEntity;

import javax.annotation.ParametersAreNonnullByDefault;

public class SakuraWallSign extends WallSignBlock implements EntityBlock {

    public SakuraWallSign(Properties properties) {
        super(properties, WoodType.OAK);
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UtilcraftSignBlockEntity(blockPos, blockState);
    }
}
