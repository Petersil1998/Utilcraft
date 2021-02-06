package net.petersil98.utilcraft.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.capabilities.home.HomeProvider;
import net.petersil98.utilcraft.data.capabilities.last_death.LastDeathProvider;
import net.petersil98.utilcraft.data.capabilities.vein_miner.VeinMinerProvider;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class AttachCapabilities {

    @SubscribeEvent
    public static void attachVeinMinerCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            VeinMinerProvider provider = new VeinMinerProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "active"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void attachHomeCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            HomeProvider provider = new HomeProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "home"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void attachLastDeathCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            LastDeathProvider provider = new LastDeathProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "last_death"), provider);
            event.addListener(provider::invalidate);
        }
    }
}
