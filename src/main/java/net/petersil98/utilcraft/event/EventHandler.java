package net.petersil98.utilcraft.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.data.ModWorldSavedData;
import net.petersil98.utilcraft.data.tileEntityOwner.CapabilityTileEntityOwner;
import net.petersil98.utilcraft.data.tileEntityOwner.TileEntityOwnerProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.petersil98.utilcraft.utils.VeinMinerUtils.*;

@Mod.EventBusSubscriber(modid = "utilcraft")
public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void veinMiner(final BlockEvent.BreakEvent event) {
        Block minedBlock = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld().getWorld();
        ItemStack mainItem = player.getHeldItemMainhand();
        if(playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world)){
            ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
            if(isSuperTool(mainItem.getItem())){
                get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
            }
            if(minedBlock instanceof OreBlock || minedBlock instanceof RedstoneOreBlock) {
                getVein(event.getPos(), blocksToHarvest, world);
            }
            for (BlockPos blockpos: blocksToHarvest) {
                BlockState blockstate = world.getBlockState(blockpos);
                if(playerCanHarvestBlock(blockstate, mainItem, blockpos, world)) {
                    if (mainItem.getMaxDamage() > mainItem.getDamage() + 1) {
                        if (blockstate.removedByPlayer(world, blockpos, player, true, world.getFluidState(blockpos))) {
                            Block block = blockstate.getBlock();
                            block.harvestBlock(world, player, blockpos, blockstate, null, player.getHeldItemMainhand());
                            block.onBlockHarvested(world, blockpos, blockstate, player);
                            if (!blockpos.equals(event.getPos())) {
                                player.getHeldItemMainhand().damageItem(1, player, (onBroken) -> onBroken.sendBreakAnimation(player.getActiveHand()));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockProtector(final BlockEvent.BreakEvent event) {
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
            TileEntity te = player.getEntityWorld().getTileEntity(event.getPos());
            if (te != null) {
                te.getCapability(CapabilityTileEntityOwner.OWNER_CAPABILITY).ifPresent(iTileEntityOwner -> {
                    UUID ownerUUID = iTileEntityOwner.getOwner();
                    UUID playerUUID = player.getGameProfile().getId();
                    if (ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                        ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
                        List<UUID> trustedPlayers = worldSavedData.getTrustedPlayerUUIDs(ownerUUID);
                        if(!trustedPlayers.contains(playerUUID)){
                            player.sendStatusMessage(new TranslationTextComponent("owner_capability.utilcraft.block_protected"), true);
                            event.setCanceled(true);
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void attachOwnerToTileEntities(AttachCapabilitiesEvent<TileEntity> event){
        TileEntityOwnerProvider provider = new TileEntityOwnerProvider();
        event.addCapability(new ResourceLocation(Main.MOD_ID, "owner"), provider);
        event.addListener(provider::invalidate);
    }

    @SubscribeEvent
    public static void onPlayerPlacesTileEntity(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity)event.getEntity();
            BlockPos pos = event.getBlockSnapshot().getPos();
            TileEntity te = player.getEntityWorld().getTileEntity(pos);
            if(te != null){
                te.getCapability(CapabilityTileEntityOwner.OWNER_CAPABILITY).ifPresent(iTileEntityOwner -> {
                    iTileEntityOwner.setOwner(player.getGameProfile().getId());
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
            TileEntity te = player.getServerWorld().getTileEntity(event.getPos());
            if(te != null){
                te.getCapability(CapabilityTileEntityOwner.OWNER_CAPABILITY).ifPresent(iTileEntityOwner -> {
                    UUID ownerUUID = iTileEntityOwner.getOwner();
                    UUID playerUUID = player.getGameProfile().getId();
                    if(ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                        ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
                        List<UUID> trustedPlayers = worldSavedData.getTrustedPlayerUUIDs(ownerUUID);
                        if(!trustedPlayers.contains(playerUUID)){
                            player.sendStatusMessage(new TranslationTextComponent("owner_capability.utilcraft.block_protected"), true);
                            event.setUseBlock(Event.Result.DENY);
                        }
                    }
                });
            }
        }
    }
}
