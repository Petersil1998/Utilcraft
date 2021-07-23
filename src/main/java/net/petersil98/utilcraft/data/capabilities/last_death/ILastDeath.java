package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public interface ILastDeath {

    void setDeathPoint(BlockPos deathPoint);

    BlockPos getDeathPoint();

    void setDeathDimension(ResourceLocation dimension);

    ResourceLocation getDeathDimension();
}
