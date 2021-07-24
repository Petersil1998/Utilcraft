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
        IVeinMiner instance = veinMinerOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("active", instance.getVeinMiner());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        IVeinMiner instance = veinMinerOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        boolean charge = nbt.getBoolean("active");
        instance.setVeinMiner(charge);
    }
}