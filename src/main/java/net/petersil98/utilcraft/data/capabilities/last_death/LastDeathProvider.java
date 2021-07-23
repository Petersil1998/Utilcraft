package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LastDeathProvider implements ICapabilitySerializable<CompoundTag> {

    private final DefaultLastDeath lastDeath = new DefaultLastDeath();
    private final LazyOptional<ILastDeath> lastDeathOptional = LazyOptional.of(() -> lastDeath);

    public void invalidate() {
        lastDeathOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityLastDeath.LAST_DEATH_CAPABILITY) {
            return lastDeathOptional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        if (CapabilityLastDeath.LAST_DEATH_CAPABILITY == null) {
            return new CompoundTag();
        } else {
            return (CompoundTag) CapabilityLastDeath.LAST_DEATH_CAPABILITY.writeNBT(lastDeath, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (CapabilityLastDeath.LAST_DEATH_CAPABILITY != null) {
            CapabilityLastDeath.LAST_DEATH_CAPABILITY.readNBT(lastDeath, null, nbt);
        }
    }
}