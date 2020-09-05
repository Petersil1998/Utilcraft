package net.petersil98.utilcraft.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.data.tileEntityOwner.CapabilityTileEntityOwner;
import net.petersil98.utilcraft.data.tileEntityOwner.TileEntityOwnerProvider;
import net.petersil98.utilcraft.data.trustedPlayers.CapabilityTrustedPlayers;
import net.petersil98.utilcraft.data.trustedPlayers.TrustedPlayersProvider;

import java.util.ArrayList;

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
        TileEntity te = event.getPlayer().getEntityWorld().getTileEntity(event.getPos());
        PlayerEntity player = event.getPlayer();
        if(te != null){
            te.getCapability(CapabilityTileEntityOwner.OWNER_CAPABILITY).ifPresent(iTileEntityOwner -> {
                PlayerEntity owner = iTileEntityOwner.getOwner();
                if(owner != null && !owner.equals(player)) {
                    owner.getCapability(CapabilityTrustedPlayers.TRUSTED_PLAYERS_CAPABILITY).ifPresent(iTrustedPlayers -> {
                        if (!iTrustedPlayers.getTrustedPlayers().contains(player)) {
                            player.sendStatusMessage(new TranslationTextComponent("owner_capability.utilcraft.block_protected"), true);
                            event.setCanceled(true);
                        }
                    });
                }
            });
        }
    }

    @SubscribeEvent
    public static void attachOwnerToTileEntities(AttachCapabilitiesEvent<TileEntity> event){
        TileEntityOwnerProvider provider = new TileEntityOwnerProvider();
        event.addCapability(new ResourceLocation(Main.MOD_ID, "owner"), provider);
        event.addListener(provider::invalidate);
    }

    @SubscribeEvent
    public static void attachTrustedPlayersToPlayers(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity) {
            TrustedPlayersProvider provider = new TrustedPlayersProvider();
            event.addCapability(new ResourceLocation(Main.MOD_ID, "trusted_players"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onPlayerPlacesTileEntity(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity)event.getEntity();
            BlockPos pos = event.getBlockSnapshot().getPos();
            TileEntity te = player.getEntityWorld().getTileEntity(pos);
            if(te != null){
                te.getCapability(CapabilityTileEntityOwner.OWNER_CAPABILITY).ifPresent(iTileEntityOwner -> {
                    iTileEntityOwner.setOwner(player);
                });
            }
        }
    }
}
