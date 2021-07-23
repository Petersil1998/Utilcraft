package net.petersil98.utilcraft.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChunkLoader extends Block {

    public static final int RADIUS = 1;

    public ChunkLoader() {
        super(AbstractBlock.Properties
                .of(Material.STONE)
        );
    }

    @Override
    public void setPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        if(world instanceof ServerWorld) {
            for(int i = 0; i < RADIUS; i++) {
                Chunk chunk = world.getChunkAt(pos);
                ((ServerWorld) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
            }
        }
    }

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if(world instanceof ServerWorld) {
            for(int i = 0; i < RADIUS; i++) {
                Chunk chunk = world.getChunkAt(pos);
                ((ServerWorld) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, false);
            }
        }
    }
}
