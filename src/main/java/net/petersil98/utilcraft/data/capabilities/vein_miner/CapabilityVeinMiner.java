package net.petersil98.utilcraft.data.capabilities.vein_miner;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityVeinMiner {
    @CapabilityInject(IVeinMiner.class)
    public static Capability<IVeinMiner> VEIN_MINER_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IVeinMiner.class, new Storage(), DefaultVeinMiner::new);
    }

    public static class Storage implements Capability.IStorage<IVeinMiner> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IVeinMiner> capability, @Nonnull IVeinMiner instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putBoolean("active", instance.getVeinMiner());
            return tag;
        }

        @Override
        public void readNBT(Capability<IVeinMiner> capability, @Nonnull IVeinMiner instance, Direction side, INBT nbt) {
            boolean charge = ((CompoundNBT) nbt).getBoolean("active");
            instance.setVeinMiner(charge);
        }
    }
}
