package net.petersil98.utilcraft.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class UtilcraftSignBlockEntity extends SignBlockEntity {

    public UtilcraftSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return UtilcraftBlockEntities.UTILCRAFT_SIGN.get();
    }
}
