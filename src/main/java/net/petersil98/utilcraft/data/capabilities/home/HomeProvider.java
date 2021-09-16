package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraft.core.BlockPos;
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
        IHome instance = homeOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        CompoundTag tag = new CompoundTag();
        BlockPos home = instance.getHome();
        if(home != null) {
            int[] cords = {home.getX(), home.getY(), home.getZ()};
            tag.putIntArray("home", cords);
        }
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        IHome instance = homeOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        int[] cords = nbt.getIntArray("home");
        BlockPos home = BlockPos.ZERO;
        if(cords.length == 3) {
            home = new BlockPos(cords[0], cords[1], cords[2]);
        }
        instance.setHome(home);
    }
}