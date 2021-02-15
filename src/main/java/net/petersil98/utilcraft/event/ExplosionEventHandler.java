package net.petersil98.utilcraft.event;

import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.SecureChest;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class ExplosionEventHandler {

    @SubscribeEvent
    public static void onExplosionEvent(@Nonnull ExplosionEvent.Detonate event) {
        ServerWorld world = (ServerWorld)event.getWorld();
        event.getAffectedBlocks().removeIf(current -> world.getBlockState(current).getBlock() instanceof SecureChest);
    }
}
