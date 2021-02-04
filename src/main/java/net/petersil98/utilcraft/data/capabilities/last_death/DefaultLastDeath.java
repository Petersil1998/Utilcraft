package net.petersil98.utilcraft.data.capabilities.last_death;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class DefaultLastDeath implements ILastDeath {

    private BlockPos deathPoint;
    private ResourceLocation dimension;

    @Override
    public void setDeathPoint(BlockPos deathPoint) {
        this.deathPoint = deathPoint;
    }

    @Override
    public BlockPos getDeathPoint() {
        return deathPoint;
    }

    @Override
    public void setDeathDimension(ResourceLocation dimension) {
        this.dimension = dimension;
    }

    @Override
    public ResourceLocation getDeathDimension() {
        return dimension;
    }
}
