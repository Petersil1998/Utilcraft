package net.petersil98.utilcraft.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.*;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.SimplePlayer;
import net.petersil98.utilcraft.data.UtilcraftWorldSavedData;
import net.petersil98.utilcraft.data.capabilities.home.CapabilityHome;
import net.petersil98.utilcraft.data.capabilities.home.HomeProvider;
import net.petersil98.utilcraft.data.capabilities.vein_miner.CapabilityVeinMiner;
import net.petersil98.utilcraft.data.capabilities.vein_miner.VeinMinerProvider;
import net.petersil98.utilcraft.gamerules.UtilcraftGameRules;
import net.petersil98.utilcraft.network.PacketHandler;
import net.petersil98.utilcraft.network.PlayerDeathStats;
import net.petersil98.utilcraft.network.ToggleVeinMiner;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;
import net.petersil98.utilcraft.utils.PlayerUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static net.petersil98.utilcraft.utils.VeinMinerUtils.*;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class EventHandler {

    private static final Method resetRainAndThunder = ObfuscationReflectionHelper.findMethod(ServerWorld.class, "func_73051_P");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void veinMiner(final BlockEvent.BreakEvent event) {
        Block minedBlock = event.getState().getBlock();
        AtomicBoolean veinMinerActive = new AtomicBoolean(false);
        if(event.getPlayer().getEntityWorld() instanceof ServerWorld) {
            ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
            ServerWorld world = player.getServerWorld();
            ItemStack mainItem = player.getHeldItemMainhand();
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
                    UtilcraftWorldSavedData worldSavedData = UtilcraftWorldSavedData.get(player.getServerWorld());
                    List<SimplePlayer> trustedPlayers = worldSavedData.getTrustedPlayers(ownerUUID);
                    if(trustedPlayers.size() == 0 || trustedPlayers.stream().noneMatch(simplePlayer -> simplePlayer.getUUID().equals(playerUUID))){
                        player.sendStatusMessage(new TranslationTextComponent(String.format("protection.%s.block_protected", Utilcraft.MOD_ID)), true);
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
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if(event.world instanceof ServerWorld && !event.world.getGameRules().getBoolean(UtilcraftGameRules.DO_ALL_PLAYERS_NEED_SLEEP)) {
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
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            PlayerUtils.setPlayerDeaths(event.getPlayer().getServer(), (ServerPlayerEntity) event.getEntity());
            PacketHandler.sendToClients(new PlayerDeathStats());
        }

        PlayerEntity original = event.getOriginal();
        PlayerEntity clone = event.getPlayer();

        AtomicReference<Boolean> veinMiner = new AtomicReference<>();

        original.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
            veinMiner.set(iVeinMiner.getVeinMiner());
        });

        clone.getCapability(CapabilityVeinMiner.VEIN_MINER_CAPABILITY).ifPresent(iVeinMiner -> {
            iVeinMiner.setVeinMiner(veinMiner.get());
        });

        AtomicReference<BlockPos> blockPos = new AtomicReference<>();

        original.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            blockPos.set(iHome.getHome());
        });

        clone.getCapability(CapabilityHome.HOME_CAPABILITY).ifPresent(iHome -> {
            iHome.setHome(blockPos.get());
        });
    }

    @SubscribeEvent
    public static void onPlayerLoginEvent(EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof ServerPlayerEntity) {
            PlayerUtils.setPlayerDeaths(event.getEntity().getServer(), (ServerPlayerEntity) event.getEntity());
            PacketHandler.sendToClients(new PlayerDeathStats());
        }
    }

    @SubscribeEvent
    public static void onExplosionEvent(ExplosionEvent.Detonate event) {
        ServerWorld world = (ServerWorld)event.getWorld();
        event.getAffectedBlocks().removeIf(current -> world.getBlockState(current).getBlock() instanceof SecureChest);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addElementsToGUI(RenderGameOverlayEvent event) {
        if(!Minecraft.getInstance().gameSettings.showDebugInfo) {
            addVeinMinerOverlay(event.getType(), event.getMatrixStack(), 10, event.getWindow().getScaledHeight()-20);
            addDeathsOverlay(event.getType(), event.getMatrixStack(), event.getWindow());
        }
    }

    @SubscribeEvent
    public static void toggleVeinMiner(TickEvent.ClientTickEvent event) {
        if(KeyBindings.VEIN_MINER.isPressed() && Minecraft.getInstance().currentScreen == null && !Minecraft.getInstance().gameSettings.showDebugInfo) {
            Utilcraft.isVeinMinerActive = !Utilcraft.isVeinMinerActive;
            PacketHandler.sendToServer(new ToggleVeinMiner(Minecraft.getInstance().player.getUniqueID(), Utilcraft.isVeinMinerActive));
        }
    }

    @SubscribeEvent
    public static void attachVeinMinerCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            VeinMinerProvider provider = new VeinMinerProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "active"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void attachHomeCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayerEntity) {
            HomeProvider provider = new HomeProvider();
            event.addCapability(new ResourceLocation(Utilcraft.MOD_ID, "home"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void addVeinMinerOverlay(RenderGameOverlayEvent.ElementType type, MatrixStack matrixStack, int x, int y) {
        if (type.equals(RenderGameOverlayEvent.ElementType.ALL)) {
            TextFormatting format = Utilcraft.isVeinMinerActive ? TextFormatting.GREEN : TextFormatting.RED;
            AbstractGui.drawString(
                    matrixStack,
                    Minecraft.getInstance().fontRenderer,
                    new TranslationTextComponent(String.format("vein_miner.%s.%s", Utilcraft.MOD_ID, Utilcraft.isVeinMinerActive ? "active" : "inactive")).mergeStyle(format),
                    x, y, 0xffffff);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void addDeathsOverlay(RenderGameOverlayEvent.ElementType type, MatrixStack matrixStack, MainWindow window) {
        if (type.equals(RenderGameOverlayEvent.ElementType.ALL)) {
            Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
            FontRenderer renderer = Minecraft.getInstance().fontRenderer;
            int height = window.getScaledHeight()/2;
            int lineHeight = renderer.FONT_HEIGHT+2;
            int offset = playerDeaths.size()/2 * lineHeight;
            int i = 0;
            for (Map.Entry<String, Integer> playerDeath: playerDeaths.entrySet()) {
                String message = String.format("%s: %d", playerDeath.getKey(), playerDeath.getValue());
                int y = height-offset + i *lineHeight;
                int x = window.getScaledWidth() - renderer.getStringWidth(message)-10;
                AbstractGui.drawString(
                        matrixStack,
                        renderer,
                        new StringTextComponent(message),
                        x, y, 0xffffff);
                i++;
            }
        }
    }
}
