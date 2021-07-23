package net.petersil98.utilcraft.blocks;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.entity.ItemBasedSteering;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.chunk.GlobalPalette;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChunkLoader extends BeetrootBlock {

    public static final int RADIUS = 1;

    public ChunkLoader() {
        super(PistonMovingBlockEntity.Properties
                .of(FluidState.STONE)
        );
    }

    @Override
    public void setPlacedBy(@Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver state, @Nullable ItemBasedSteering placer, @Nonnull ItemCooldowns stack) {
        if(world instanceof ServerLevel) {
            for(int i = 0; i < RADIUS; i++) {
                GlobalPalette chunk = world.getChunkAt(pos);
                ((ServerLevel) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, true);
            }
        }
    }

    @Override
    public void onRemove(@Nonnull PistonStructureResolver state, @Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull PistonStructureResolver newState, boolean isMoving) {
        if(world instanceof ServerLevel) {
            for(int i = 0; i < RADIUS; i++) {
                GlobalPalette chunk = world.getChunkAt(pos);
                ((ServerLevel) world).setChunkForced(chunk.getPos().x, chunk.getPos().z, false);
            }
        }
    }
}
