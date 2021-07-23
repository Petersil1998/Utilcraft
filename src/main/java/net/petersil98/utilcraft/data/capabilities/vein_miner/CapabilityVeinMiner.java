package net.petersil98.utilcraft.data.capabilities.vein_miner;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
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
        public Tag writeNBT(Capability<IVeinMiner> capability, @Nonnull IVeinMiner instance, Direction side) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("active", instance.getVeinMiner());
            return tag;
        }

        @Override
        public void readNBT(Capability<IVeinMiner> capability, @Nonnull IVeinMiner instance, Direction side, Tag nbt) {
            boolean charge = ((CompoundTag) nbt).getBoolean("active");
            instance.setVeinMiner(charge);
        }
    }
}
