package net.petersil98.utilcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChunkLoader extends Block {

    public static final int RADIUS = 1;

    public ChunkLoader(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        if(world instanceof ServerLevel) {
            for(int i = 0; i < RADIUS; i++) {
                LevelChunk chunk = world.getChunkAt(pos);
                ((ServerLevel) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
            }
        }
    }

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if(world instanceof ServerLevel) {
            for(int i = 0; i < RADIUS; i++) {
                LevelChunk chunk = world.getChunkAt(pos);
                ((ServerLevel) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, false);
            }
        }
    }
}
