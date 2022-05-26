package net.petersil98.utilcraft.items;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpawnerItem extends BlockItem {

    public SpawnerItem(Properties properties) {
        super(Blocks.SPAWNER, properties);
    }

    protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos pos, @Nonnull Level world, @Nullable Player player, @Nonnull ItemStack stack, @Nonnull BlockState state) {
        return setBlockEntityNBT(world, pos, stack);
    }

    public static boolean setBlockEntityNBT(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull ItemStack stack) {
        MinecraftServer minecraftserver = world.getServer();
        if (minecraftserver != null) {
            CompoundTag entityTag = stack.getTagElement("BlockEntityTag");
            if (entityTag != null) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity != null) {
                    CompoundTag original = blockEntity.saveWithoutMetadata();
                    CompoundTag old = original.copy();
                    original.merge(entityTag);
                    original.putInt("x", pos.getX());
                    original.putInt("y", pos.getY());
                    original.putInt("z", pos.getZ());
                    if (!original.equals(old)) {
                        blockEntity.load(original);
                        blockEntity.setChanged();
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return "item.utilcraft.spawner_item";
    }
}
