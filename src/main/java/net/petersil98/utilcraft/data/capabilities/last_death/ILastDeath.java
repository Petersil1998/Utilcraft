package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public interface ILastDeath {

    void setDeathPoint(BlockPos deathPoint);

    BlockPos getDeathPoint();

    void setDeathDimension(ResourceLocation dimension);

    ResourceLocation getDeathDimension();
}
