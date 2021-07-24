package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.petersil98.utilcraft.data.capabilities.vein_miner.IVeinMiner;

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
        ILastDeath instance = lastDeathOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        CompoundTag tag = new CompoundTag();
        BlockPos deathPoint = instance.getDeathPoint();
        ResourceLocation deathDimension = instance.getDeathDimension();
        if(deathPoint != null && deathDimension != null) {
            tag.putLong("deathPoint", deathPoint.asLong());
            tag.putString("deathDimension", deathDimension.toString());
        }
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ILastDeath instance = lastDeathOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        if(nbt.contains("deathPoint", 99) && nbt.contains("deathDimension", 8)) {
            instance.setDeathPoint(BlockPos.of(nbt.getLong("deathPoint")));
            instance.setDeathDimension(new ResourceLocation(nbt.getString("deathDimension")));
        }
    }
}