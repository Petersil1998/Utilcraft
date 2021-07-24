package net.petersil98.utilcraft.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModInfo;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.network.PlayerDeathStats;
import net.petersil98.utilcraft.network.SyncDeathPoint;
import net.petersil98.utilcraft.block_entities.SecureChestTileEntity;
import net.petersil98.utilcraft.utils.PlayerUtils;
import org.apache.maven.artifact.versioning.ComparableVersion;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerRightClickBlock(@Nonnull PlayerInteractEvent.RightClickBlock event){
        if(event.getEntity() instanceof ServerPlayer){
            ServerPlayer player = (ServerPlayer)event.getEntity();
            BlockEntity te = player.getLevel().getBlockEntity(event.getPos());
            if(te instanceof SecureChestTileEntity){
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getGameProfile().getId();
                if(ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.displayClientMessage(new TranslatableComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
                        event.setUseBlock(Event.Result.DENY);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerPlayerCloneEvent(@Nonnull PlayerEvent.Clone event) {
        AttachCapabilities.adjustCapabilities(event.getOriginal(), event.getPlayer());
        if(event.isWasDeath()) {
            PlayerUtils.setPlayerDeaths(event.getPlayer().getServer(), (ServerPlayer) event.getEntity());
            NetworkManager.sendToClients(new PlayerDeathStats());

            event.getPlayer().getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                iLastDeath.setDeathPoint(event.getOriginal().blockPosition());
                iLastDeath.setDeathDimension(event.getOriginal().level.dimension().location());
                if(iLastDeath.getDeathPoint() != null && iLastDeath.getDeathDimension() != null) {
                    NetworkManager.sendToClient(new SyncDeathPoint(event.getOriginal().blockPosition(), event.getOriginal().level.dimension().location()), (ServerPlayer) event.getPlayer());
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoginEvent(@Nonnull EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            PlayerUtils.setPlayerDeaths(player.getServer(), player);
            NetworkManager.sendToClients(new PlayerDeathStats());
            event.getEntity().getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                if(iLastDeath.getDeathPoint() != null && iLastDeath.getDeathDimension() != null) {
                    NetworkManager.sendToClient(new SyncDeathPoint(iLastDeath.getDeathPoint(), iLastDeath.getDeathDimension()), player);
                }
            });

            AtomicReference<IModInfo> modInfo = new AtomicReference<>();
            ModList.get().getModContainerById(Utilcraft.MOD_ID).ifPresent(modContainer -> modInfo.set(modContainer.getModInfo()));
            VersionChecker.CheckResult result = VersionChecker.getResult(modInfo.get());
            if(result.status() == VersionChecker.Status.OUTDATED) {
                List<ComparableVersion> sortedKeys = new ArrayList<>(result.changes().keySet());
                Collections.sort(sortedKeys);
                player.displayClientMessage(new TranslatableComponent(String.format("version.%s.new", Utilcraft.MOD_ID), sortedKeys.get(0).toString()), false);
            }
        }
    }
}
