package net.petersil98.utilcraft.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public abstract class AbstractSuperTool extends Item {

    public AbstractSuperTool(Properties properties) {
        super(properties);
    }

    public static BlockRayTraceResult rayTracer(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
        return rayTrace(worldIn, player, fluidMode);
    }
}
