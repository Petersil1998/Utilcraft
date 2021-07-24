package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

import net.minecraft.world.item.Item.Properties;

public abstract class AbstractSuperTool extends Item {

    public AbstractSuperTool(Properties properties) {
        super(properties);
    }

    @Nonnull
    public static BlockHitResult rayTracer(Level world, Player player, ClipContext.Fluid fluidMode) {
        return getPlayerPOVHitResult(world, player, fluidMode);
    }
}
