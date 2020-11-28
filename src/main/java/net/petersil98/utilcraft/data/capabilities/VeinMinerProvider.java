package net.petersil98.utilcraft.data.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VeinMinerProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultVeinMiner veinMiner = new DefaultVeinMiner();
    private final LazyOptional<IVeinMiner> chargeOptional = LazyOptional.of(() -> veinMiner);

    public void invalidate() {
        chargeOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return chargeOptional.cast();
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