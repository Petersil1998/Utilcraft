package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
        public INBT writeNBT(Capability<ILastDeath> capability, @Nonnull ILastDeath instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            BlockPos deathPoint = instance.getDeathPoint();
            ResourceLocation deathDimension = instance.getDeathDimension();
            if(deathPoint != null && deathDimension != null) {
                tag.putLong("deathPoint", deathPoint.asLong());
                tag.putString("deathDimension", deathDimension.toString());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<ILastDeath> capability, @Nonnull ILastDeath instance, Direction side, INBT nbt) {
            CompoundNBT tag = ((CompoundNBT)nbt);
            if(tag.contains("deathPoint", 99) && tag.contains("deathDimension", 8)) {
                instance.setDeathPoint(BlockPos.of(tag.getLong("deathPoint")));
                instance.setDeathDimension(new ResourceLocation(tag.getString("deathDimension")));
            }
        }
    }
}
