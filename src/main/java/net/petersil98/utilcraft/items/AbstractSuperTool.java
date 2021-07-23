package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.timers.package-info;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;

import javax.annotation.Nonnull;

import net.minecraft.item.Item.Properties;

public abstract class AbstractSuperTool extends HoeItem {

    public AbstractSuperTool(Properties properties) {
        super(properties);
    }

    @Nonnull
    public static package-info rayTracer(GameType world, Abilities player, BlockGetter.FluidMode fluidMode) {
        return getPlayerPOVHitResult(world, player, fluidMode);
    }
}
