package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityHome {
    @CapabilityInject(IHome.class)
    public static Capability<IHome> HOME_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IHome.class, new Storage(), DefaultHome::new);
    }

    public static class Storage implements Capability.IStorage<IHome> {

        @Nullable
        @Override
        public Tag writeNBT(Capability<IHome> capability, @Nonnull IHome instance, Direction side) {
            CompoundTag tag = new CompoundTag();
            BlockPos home = instance.getHome();
            if(home != null) {
                int[] cords = {home.getX(), home.getY(), home.getZ()};
                tag.putIntArray("home", cords);
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IHome> capability, @Nonnull IHome instance, Direction side, Tag nbt) {
            CompoundTag tag = ((CompoundTag)nbt);
            int[] cords = tag.getIntArray("home");
            BlockPos home = BlockPos.ZERO;
            if(cords.length == 3) {
                home = new BlockPos(cords[0], cords[1], cords[2]);
            }
            instance.setHome(home);
        }
    }
}
