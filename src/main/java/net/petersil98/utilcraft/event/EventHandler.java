package net.petersil98.utilcraft.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.ModWorldSavedData;
import net.petersil98.utilcraft.data.capabilities.CapabilityVeinMiner;
import net.petersil98.utilcraft.data.capabilities.VeinMinerProvider;
import net.petersil98.utilcraft.network.PacketHandler;
import net.petersil98.utilcraft.network.ToggleVeinMiner;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static net.petersil98.utilcraft.utils.VeinMinerUtils.*;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class EventHandler {

    private static final Method resetRainAndThunder = ObfuscationReflectionHelper.findMethod(ServerWorld.class, "func_73051_P");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void veinMiner(final BlockEvent.BreakEvent event) {
        Block minedBlock = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        AtomicBoolean veinMinerActive = new AtomicBoolean(false);
        player.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
            veinMinerActive.set(iVeinMiner.getVeinMiner());
        });
        if(veinMinerActive.get() && player.getEntityWorld() instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) player.getEntityWorld();
            ItemStack mainItem = player.getHeldItemMainhand();
            if (playerCanHarvestBlock(event.getState(), mainItem, event.getPos(), world, player)) {
                ArrayList<BlockPos> blocksToHarvest = new ArrayList<>();
                if (isSuperTool(mainItem.getItem())) {
                    get3x3FieldAroundTargetedBlock(player, blocksToHarvest);
                }
                if (isOreBlock(minedBlock)) {
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
                        player.sendStatusMessage(new TranslationTextComponent("protection.utilcraft.block_protected"), true);
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
                        player.sendStatusMessage(new TranslationTextComponent("protection.utilcraft.block_protected"), true);
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

                    if (world.getGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE)) {
                        try {
                            resetRainAndThunder.invoke(world);
                        } catch (IllegalAccessException | InvocationTargetException ignored) {}
                    }

                    world.getPlayers().stream().filter(LivingEntity::isSleeping).collect(Collectors.toList()).forEach((p_241131_0_) -> p_241131_0_.stopSleepInBed(false, false));
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addElementsToGUI(RenderGameOverlayEvent event) {
        if(!Minecraft.getInstance().gameSettings.showDebugInfo) {
            addVeinMinerOverlay(event.getType(), event.getMatrixStack(), 10, event.getWindow().getScaledHeight()-20);
        }
    }

    @SubscribeEvent
    public static void toggleVeinMiner(InputEvent.KeyInputEvent event) {
        if(event.getKey() == KeyBindings.VEIN_MINER.getKey().getKeyCode() && event.getAction() == GLFW.GLFW_PRESS) {
            Main.isVeinMinerActive = !Main.isVeinMinerActive;
            PacketHandler.sendToServer(new ToggleVeinMiner(Minecraft.getInstance().player.getUniqueID(), Main.isVeinMinerActive));
        }
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            VeinMinerProvider provider = new VeinMinerProvider();
            event.addCapability(new ResourceLocation(Main.MOD_ID, "active"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void addVeinMinerOverlay(RenderGameOverlayEvent.ElementType type, MatrixStack matrixStack, int x, int y) {
        if (type.equals(RenderGameOverlayEvent.ElementType.ALL)) {
            AbstractGui.drawString(
                    matrixStack,
                    Minecraft.getInstance().fontRenderer,
                    new TranslationTextComponent(String.format("vein_miner.utilcraft.%s", Main.isVeinMinerActive ? "active" : "inactive")),
                    x, y, 0xffffff);
        }
    }
}
