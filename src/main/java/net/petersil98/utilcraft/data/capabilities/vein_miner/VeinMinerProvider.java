package net.petersil98.utilcraft.data.capabilities.vein_miner;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VeinMinerProvider implements ICapabilitySerializable<CompoundTag> {

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
    public CompoundTag serializeNBT() {
        if (CapabilityVeinMiner.VEIN_MINER_CAPABILITY == null) {
            return new CompoundTag();
        } else {
            return (CompoundTag) CapabilityVeinMiner.VEIN_MINER_CAPABILITY.writeNBT(veinMiner, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (CapabilityVeinMiner.VEIN_MINER_CAPABILITY != null) {
            CapabilityVeinMiner.VEIN_MINER_CAPABILITY.readNBT(veinMiner, null, nbt);
        }
    }
}