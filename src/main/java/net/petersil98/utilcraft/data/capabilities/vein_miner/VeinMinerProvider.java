package net.petersil98.utilcraft.data.capabilities.vein_miner;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VeinMinerProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultVeinMiner veinMiner = new DefaultVeinMiner();
    private final LazyOptional<IVeinMiner> veinMinerOptional = LazyOptional.of(() -> veinMiner);

    public void invalidate() {
        veinMinerOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityVeinMiner.VEIN_MINER_CAPABILITY) {
            return veinMinerOptional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityVeinMiner.VEIN_MINER_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityVeinMiner.VEIN_MINER_CAPABILITY.writeNBT(veinMiner, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityVeinMiner.VEIN_MINER_CAPABILITY != null) {
            CapabilityVeinMiner.VEIN_MINER_CAPABILITY.readNBT(veinMiner, null, nbt);
        }
    }
}