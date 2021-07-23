package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HomeProvider implements ICapabilitySerializable<CompoundTag> {

    private final DefaultHome home = new DefaultHome();
    private final LazyOptional<IHome> homeOptional = LazyOptional.of(() -> home);

    public void invalidate() {
        homeOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityHome.HOME_CAPABILITY) {
            return homeOptional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        if (CapabilityHome.HOME_CAPABILITY == null) {
            return new CompoundTag();
        } else {
            return (CompoundTag) CapabilityHome.HOME_CAPABILITY.writeNBT(home, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (CapabilityHome.HOME_CAPABILITY != null) {
            CapabilityHome.HOME_CAPABILITY.readNBT(home, null, nbt);
        }
    }
}