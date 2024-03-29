package net.petersil98.utilcraft.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.data.KeyBindings;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.gamerules.UtilcraftGameRules;
import net.petersil98.utilcraft.network.NetworkManager;
import net.petersil98.utilcraft.network.SyncDeathPoint;
import net.petersil98.utilcraft.network.ToggleVeinMiner;
import net.petersil98.utilcraft.utils.PlayerUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class TickEventHandler {

    private static final Method resetRainAndThunder = ObfuscationReflectionHelper.findMethod(ServerWorld.class, "func_73051_P");

    @SubscribeEvent
    public static void onWorldTick(@Nonnull TickEvent.WorldTickEvent event) {
        if(event.world instanceof ServerWorld && !event.world.getGameRules().getBoolean(UtilcraftGameRules.DO_ALL_PLAYERS_NEED_SLEEP)) {
            ServerWorld world = (ServerWorld) event.world;
            List<ServerPlayerEntity> players = world.getServer().getPlayerList().getPlayers();
            for (ServerPlayerEntity player : players) {
                if (player.isSleepingLongEnough()) {
                    if (world.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
                        long l = world.getDayTime() + 24000L;
                        world.setDayTime(ForgeEventFactory.onSleepFinished(world, l - l % 24000L, world.getDayTime()));
                    }

                    if (world.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE)) {
                        try {
                            resetRainAndThunder.invoke(world);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            Utilcraft.LOGGER.error("Couldn't reset rain and thunder timers after skipping night", e);
                        }
                    }

                    world.players().stream().filter(LivingEntity::isSleeping).collect(Collectors.toList()).forEach((playerEntity) -> playerEntity.stopSleepInBed(false, false));
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTickEvent(@Nonnull TickEvent.PlayerTickEvent event) {
        if(event.side.isServer() && event.phase == TickEvent.Phase.END) {
            event.player.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(iLastDeath -> {
                if(iLastDeath.getDeathPoint() != null && iLastDeath.getDeathDimension() != null && event.player.level.dimension().location().equals(iLastDeath.getDeathDimension())) {
                    BlockPos pos = event.player.blockPosition();
                    if(iLastDeath.getDeathPoint().closerThan(pos, 2)) {
                        iLastDeath.setDeathPoint(null);
                        iLastDeath.setDeathDimension(null);
                        NetworkManager.sendToClient(new SyncDeathPoint(null, null), (ServerPlayerEntity) event.player);
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void toggleVeinMiner(@Nonnull TickEvent.ClientTickEvent event) {
        if(KeyBindings.VEIN_MINER.consumeClick() && Minecraft.getInstance().screen == null && !Minecraft.getInstance().options.renderDebug) {
            PlayerUtils.setVeinMinerActive(!PlayerUtils.isVeinMinerActive());
            NetworkManager.sendToServer(new ToggleVeinMiner(Minecraft.getInstance().player.getUUID(), PlayerUtils.isVeinMinerActive()));
        }
    }
}
