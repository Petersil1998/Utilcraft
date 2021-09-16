package net.petersil98.utilcraft.event;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;
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
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;
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
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
            TileEntity te = player.getLevel().getBlockEntity(event.getPos());
            if(te instanceof SecureChestTileEntity){
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getGameProfile().getId();
                if(ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.displayClientMessage(new TranslationTextComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
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
            PlayerUtils.setPlayerDeaths(event.getPlayer().getServer(), (ServerPlayerEntity) event.getEntity());
            NetworkManager.sendToClients(new PlayerDeathStats());

            event.getPlayer().getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                iLastDeath.setDeathPoint(event.getOriginal().blockPosition());
                iLastDeath.setDeathDimension(event.getOriginal().level.dimension().location());
                if(iLastDeath.getDeathPoint() != null && iLastDeath.getDeathDimension() != null) {
                    NetworkManager.sendToClient(new SyncDeathPoint(event.getOriginal().blockPosition(), event.getOriginal().level.dimension().location()), (ServerPlayerEntity) event.getPlayer());
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoginEvent(@Nonnull EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
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
            if(result.status == VersionChecker.Status.OUTDATED) {
                List<ComparableVersion> sortedKeys = new ArrayList<>(result.changes.keySet());
                Collections.sort(sortedKeys);
                player.displayClientMessage(new TranslationTextComponent(String.format("version.%s.new", Utilcraft.MOD_ID), sortedKeys.get(0).toString()), false);
            }
        }
    }

    /*@SubscribeEvent
    public static void onChunkLoaded(@Nonnull ChunkEvent.Load event){
        BlockPos.Mutable blockPos = new BlockPos.Mutable(0,0,0);
        for(int i = 0; i < event.getChunk().getHeight(Heightmap.Type.WORLD_SURFACE, 0, 0); i++) {
            event.getChunk().setBlockState(blockPos.move(Direction.UP), Blocks.AIR.defaultBlockState(), true);
        }
    }*/

    //TODO
    /*@SubscribeEvent
    public static void onPlayerKnockBack(@Nonnull LivingKnockBackEvent event) {
        if(event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
            for(ItemStack armorSlot: player.getArmorSlots()) {
                if(armorSlot.getItem() instanceof ArmorItem) {
                    ArmorItem item = (ArmorItem) armorSlot.getItem();
                    if(item.getMaterial().equals(ArmorMaterial.DIAMOND)) {
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }
    }*/
}
