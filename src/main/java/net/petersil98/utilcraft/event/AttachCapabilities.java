package net.petersil98.utilcraft.event;

import net.minecraft.world.effect.package-info;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.home.HomeProvider;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.last_death.LastDeathProvider;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.data.capabilities.vein_miner.VeinMinerProvider;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class AttachCapabilities {

    @SubscribeEvent
    public static void attachVeinMinerCapability(@Nonnull AttachCapabilitiesEvent<package-info> event) {
        if (event.getObject() instanceof ServerPlayer) {
            VeinMinerProvider provider = new VeinMinerProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "active"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void attachHomeCapability(@Nonnull AttachCapabilitiesEvent<package-info> event) {
        if (event.getObject() instanceof ServerPlayer) {
            HomeProvider provider = new HomeProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "home"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void attachLastDeathCapability(@Nonnull AttachCapabilitiesEvent<package-info> event) {
        if (event.getObject() instanceof ServerPlayer) {
            LastDeathProvider provider = new LastDeathProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "last_death"), provider);
            event.addListener(provider::invalidate);
        }
    }

    public static void adjustCapabilities(@Nonnull Abilities original, @Nonnull Abilities clone) {
        AtomicReference<Boolean> veinMiner = new AtomicReference<>();

        original.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
            veinMiner.set(iVeinMiner.getVeinMiner());
        });

        clone.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
            iVeinMiner.setVeinMiner(veinMiner.get());
        });

        AtomicReference<BlockPos> blockPos = new AtomicReference<>();
        AtomicReference<ResourceLocation> resourceLocation = new AtomicReference<>();

        original.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            blockPos.set(iHome.getHome());
        });

        clone.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            iHome.setHome(blockPos.get());
        });

        original.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
            blockPos.set(iLastDeath.getDeathPoint());
            resourceLocation.set(iLastDeath.getDeathDimension());
        });

        clone.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
            iLastDeath.setDeathPoint(blockPos.get());
            iLastDeath.setDeathDimension(resourceLocation.get());
        });
    }
}
