package net.petersil98.utilcraft.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.network.PlayerDeathStats;
import net.petersil98.utilcraft.network.SyncDeathPoint;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;
import net.petersil98.utilcraft.utils.PlayerUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
            TileEntity te = player.getServerWorld().getTileEntity(event.getPos());
            if(te instanceof SecureChestTileEntity){
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getGameProfile().getId();
                if(ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getServerWorld());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.sendStatusMessage(new TranslationTextComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
                        event.setUseBlock(Event.Result.DENY);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        adjustCapabilities(event.getOriginal(), event.getPlayer());
        if(event.isWasDeath()) {
            PlayerUtils.setPlayerDeaths(event.getPlayer().getServer(), (ServerPlayerEntity) event.getEntity());
            NetworkManager.sendToClients(new PlayerDeathStats());

            event.getPlayer().getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                iLastDeath.setDeathPoint(event.getOriginal().getPosition());
                iLastDeath.setDeathDimension(event.getOriginal().world.getDimensionKey().getLocation());
                NetworkManager.sendToClient(new SyncDeathPoint(event.getOriginal().getPosition(), event.getOriginal().world.getDimensionKey().getLocation()), (ServerPlayerEntity) event.getPlayer());
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoginEvent(EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof ServerPlayerEntity) {
            PlayerUtils.setPlayerDeaths(event.getEntity().getServer(), (ServerPlayerEntity) event.getEntity());
            NetworkManager.sendToClients(new PlayerDeathStats());
        }
    }

    private static void adjustCapabilities(PlayerEntity original, PlayerEntity clone) {
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
