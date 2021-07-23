package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityLastDeath {
    @CapabilityInject(ILastDeath.class)
    public static Capability<ILastDeath> LAST_DEATH_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(ILastDeath.class, new Storage(), DefaultLastDeath::new);
    }

    public static class Storage implements Capability.IStorage<ILastDeath> {

        @Nullable
        @Override
        public Tag writeNBT(Capability<ILastDeath> capability, @Nonnull ILastDeath instance, Direction side) {
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
        public void readNBT(Capability<ILastDeath> capability, @Nonnull ILastDeath instance, Direction side, Tag nbt) {
            CompoundTag tag = ((CompoundTag)nbt);
            if(tag.contains("deathPoint", 99) && tag.contains("deathDimension", 8)) {
                instance.setDeathPoint(BlockPos.of(tag.getLong("deathPoint")));
                instance.setDeathDimension(new ResourceLocation(tag.getString("deathDimension")));
            }
        }
    }
}
