package net.petersil98.utilcraft.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
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
        Block minedBlock = event.getState().getBlock();
        AtomicBoolean veinMinerActive = new AtomicBoolean(false);
        if(event.getPlayer().getCommandSenderWorld() instanceof ServerWorld) {
            ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
            ServerWorld world = player.getLevel();
            ItemStack mainItem = player.getMainHandItem();
            player.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
                veinMinerActive.set(iVeinMiner.getVeinMiner());
            });
            if (playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world, player)) {
                ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
                boolean shouldReduceDurability = true;
                if (isSuperTool(mainItem.getItem())) {
                    get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
                    shouldReduceDurability = false;
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
                    BlockState blockstate = world.getBlockState(blockpos);
                    if (playerCanHarvestBlock(blockstate, mainItem, blockpos, world, player)) {
                        if (mainItem.getMaxDamage() > mainItem.getDamageValue() + 1) {
                            if (blockstate.removedByPlayer(world, blockpos, player, true, world.getFluidState(blockpos))) {
                                Block block = blockstate.getBlock();
                                block.playerDestroy(world, player, blockpos, blockstate, null, player.getMainHandItem());
                                block.playerWillDestroy(world, blockpos, blockstate, player);
                                int bonusLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, mainItem);
                                int silkLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, mainItem);
                                event.setExpToDrop(event.getExpToDrop()+blockstate.getExpDrop(world, blockpos, bonusLevel, silkLevel));
                                if (!blockpos.equals(event.getPos()) && shouldReduceDurability) {
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
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
            TileEntity te = player.getCommandSenderWorld().getBlockEntity(event.getPos());
            if (te instanceof SecureChestTileEntity) {
                UUID ownerUUID = ((SecureChestTileEntity)te).getOwner();
                UUID playerUUID = player.getUUID();
                if (ownerUUID != null && !ownerUUID.equals(playerUUID)) {
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getLevel());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.displayClientMessage(new TranslationTextComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
