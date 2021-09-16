package net.petersil98.utilcraft.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpawnerItem extends BlockItem {

    public SpawnerItem(Properties properties) {
        super(Blocks.SPAWNER, properties);
    }

    protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos pos, @Nonnull World world, @Nullable PlayerEntity player, @Nonnull ItemStack stack, @Nonnull BlockState state) {
        return setTileEntityNBT(world, pos, stack);
    }

    public static boolean setTileEntityNBT(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull ItemStack stack) {
        MinecraftServer minecraftserver = world.getServer();
        if (minecraftserver != null) {
            CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");
            if (compoundnbt != null) {
                TileEntity tileentity = world.getBlockEntity(pos);
                if (tileentity != null) {
                    CompoundNBT original = tileentity.save(new CompoundNBT());
                    CompoundNBT old = original.copy();
                    original.merge(compoundnbt);
                    original.putInt("x", pos.getX());
                    original.putInt("y", pos.getY());
                    original.putInt("z", pos.getZ());
                    if (!original.equals(old)) {
                        tileentity.load(world.getBlockState(pos), original);
                        tileentity.setChanged();
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
