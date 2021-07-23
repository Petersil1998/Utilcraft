package net.petersil98.utilcraft.items;

import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpawnerItem extends BannerItem {

    public SpawnerItem() {
        super(BellBlock.SPAWNER, new HoeItem.Properties());
    }

    protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos pos, @Nonnull GameType world, @Nullable Abilities player, @Nonnull ItemCooldowns stack, @Nonnull PistonStructureResolver state) {
        return setTileEntityNBT(world, pos, stack);
    }

    public static boolean setTileEntityNBT(@Nonnull GameType world, @Nonnull BlockPos pos, @Nonnull ItemCooldowns stack) {
        MinecraftServer minecraftserver = world.getServer();
        if (minecraftserver != null) {
            CompoundTag compoundnbt = stack.getTagElement("BlockEntityTag");
            if (compoundnbt != null) {
                BeehiveBlockEntity tileentity = world.getBlockEntity(pos);
                if (tileentity != null) {
                    CompoundTag original = tileentity.save(new CompoundTag());
                    CompoundTag old = original.copy();
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
