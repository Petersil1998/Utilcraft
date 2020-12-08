package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

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
        public INBT writeNBT(Capability<IHome> capability, IHome instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            BlockPos home = instance.getHome();
            if(home != null) {
                int[] cords = {home.getX(), home.getY(), home.getZ()};
                tag.putIntArray("home", cords);
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IHome> capability, IHome instance, Direction side, INBT nbt) {
            CompoundNBT tag = ((CompoundNBT)nbt);
            int[] cords = tag.getIntArray("home");
            BlockPos home = BlockPos.ZERO;
            if(cords.length == 3) {
                home = new BlockPos(cords[0], cords[1], cords[2]);
            }
            instance.setHome(home);
        }
    }
}
