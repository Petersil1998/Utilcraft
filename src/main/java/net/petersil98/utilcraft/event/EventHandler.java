package net.petersil98.utilcraft.event;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.data.ModWorldSavedData;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.petersil98.utilcraft.utils.VeinMinerUtils.*;

@Mod.EventBusSubscriber(modid = "utilcraft")
public class EventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void veinMiner(final BlockEvent.BreakEvent event) {
        Block minedBlock = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        if(player.isSneaking() && player.getEntityWorld() instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) player.getEntityWorld();
            ItemStack mainItem = player.getHeldItemMainhand();
            if (playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world, player)) {
                ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
                if (isSuperTool(mainItem.getItem())) {
                    get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
                }
                if (minedBlock instanceof OreBlock || minedBlock instanceof RedstoneOreBlock) {
                    getVein(event.getPos(), blocksToHarvest, world);
                } else if(isLogBlock(minedBlock)) {
                     getTree(event.getPos(), blocksToHarvest, world);
                }
                blocksToHarvest.remove(event.getPos());
                for (BlockPos blockpos : blocksToHarvest) {
                    BlockState blockstate = world.getBlockState(blockpos);
                    if (playerCanHarvestBlock(blockstate, mainItem, blockpos, world, player)) {
                        if (mainItem.getMaxDamage() > mainItem.getDamage() + 1) {
                            if (blockstate.removedByPlayer(world, blockpos, player, true, world.getFluidState(blockpos))) {
                                Block block = blockstate.getBlock();
                                block.harvestBlock(world, player, blockpos, blockstate, null, player.getHeldItemMainhand());
                                block.onBlockHarvested(world, blockpos, blockstate, player);
                                int bonusLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, mainItem);
                                int silklevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, mainItem);
                                event.setExpToDrop(event.getExpToDrop()+blockstate.getExpDrop(world, blockpos, bonusLevel, silklevel));
                                if (!blockpos.equals(event.getPos())) {
                                    player.getHeldItemMainhand().damageItem(1, player, (onBroken) -> onBroken.sendBreakAnimation(player.getActiveHand()));
                                }
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
            if (te instanceof SecureChestTileEntity) {
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getUniqueID();
                if (ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
                    List<UUID> trustedPlayers = worldSavedData.getTrustedPlayerUUIDs(ownerUUID);
                    if(!trustedPlayers.contains(playerUUID)){
                        player.sendStatusMessage(new TranslationTextComponent("owner_capability.utilcraft.block_protected"), true);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
            TileEntity te = player.getServerWorld().getTileEntity(event.getPos());
            if(te instanceof SecureChestTileEntity){
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getGameProfile().getId();
                if(ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    ModWorldSavedData worldSavedData = ModWorldSavedData.get(player.getServerWorld());
                    List<UUID> trustedPlayers = worldSavedData.getTrustedPlayerUUIDs(ownerUUID);
                    if(!trustedPlayers.contains(playerUUID)){
                        player.sendStatusMessage(new TranslationTextComponent("owner_capability.utilcraft.block_protected"), true);
                        event.setUseBlock(Event.Result.DENY);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if(event.world instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) event.world;
            List<ServerPlayerEntity> players = world.getServer().getPlayerList().getPlayers();
            for (ServerPlayerEntity player : players) {
                if (player.isPlayerFullyAsleep()) {
                    if (world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) {
                        long l = world.getDayTime() + 24000L;
                        world.setDayTime(ForgeEventFactory.onSleepFinished(world, l - l % 24000L, world.getDayTime()));
                    }

                    world.getPlayers().stream().filter(LivingEntity::isSleeping).collect(Collectors.toList()).forEach((p_241131_0_) -> {
                        p_241131_0_.stopSleepInBed(false, false);
                    });
                    if (world.getGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE)) {
                        world.func_241113_a_(6000,0,false, false);
                    }
                }
            }
        }
    }
}
