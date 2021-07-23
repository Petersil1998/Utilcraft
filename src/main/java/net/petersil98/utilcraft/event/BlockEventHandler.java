package net.petersil98.utilcraft.event;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.item.enchantment.DiggingEnchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.petersil98.utilcraft.utils.VeinMinerUtils.*;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class BlockEventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void veinMiner(@Nonnull final BlockEvent.BreakEvent event) {
        BeetrootBlock minedBlock = event.getState().getBlock();
        AtomicBoolean veinMinerActive = new AtomicBoolean(false);
        if(event.getPlayer().getCommandSenderWorld() instanceof ServerLevel) {
            ServerPlayer player = (ServerPlayer)event.getPlayer();
            ServerLevel world = player.getLevel();
            ItemCooldowns mainItem = player.getMainHandItem();
            player.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
                veinMinerActive.set(iVeinMiner.getVeinMiner());
            });
            if (playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world, player)) {
                ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
                if (isSuperTool(mainItem.getItem())) {
                    get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
                }
                if(veinMinerActive.get()) {
                    if (isOreBlock(minedBlock)) {
                        getVein(event.getPos(), blocksToHarvest, world);
                    } else if (isLogBlock(minedBlock)) {
                        getTree(event.getPos(), blocksToHarvest, world);
                    }
                }
                blocksToHarvest.remove(event.getPos());
                for (BlockPos blockpos : blocksToHarvest) {
                    PistonStructureResolver blockstate = world.getBlockState(blockpos);
                    if (playerCanHarvestBlock(blockstate, mainItem, blockpos, world, player)) {
                        if (mainItem.getMaxDamage() > mainItem.getDamageValue() + 1) {
                            if (blockstate.removedByPlayer(world, blockpos, player, true, world.getFluidState(blockpos))) {
                                BeetrootBlock block = blockstate.getBlock();
                                block.playerDestroy(world, player, blockpos, blockstate, null, player.getMainHandItem());
                                block.playerWillDestroy(world, blockpos, blockstate, player);
                                int bonusLevel = DiggingEnchantment.getItemEnchantmentLevel(EnchantmentCategory.BLOCK_FORTUNE, mainItem);
                                int silkLevel = DiggingEnchantment.getItemEnchantmentLevel(EnchantmentCategory.SILK_TOUCH, mainItem);
                                event.setExpToDrop(event.getExpToDrop()+blockstate.getExpDrop(world, blockpos, bonusLevel, silkLevel));
                                if (!blockpos.equals(event.getPos())) {
                                    player.getMainHandItem().hurtAndBreak(1, player, (onBroken) -> onBroken.broadcastBreakEvent(player.getUsedItemHand()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockProtector(@Nonnull final BlockEvent.BreakEvent event) {
        if(event.getPlayer() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)event.getPlayer();
            BeehiveBlockEntity te = player.getCommandSenderWorld().getBlockEntity(event.getPos());
            if (te instanceof SecureChestTileEntity) {
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getUUID();
                if (ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.displayClientMessage(new TranslatableComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
