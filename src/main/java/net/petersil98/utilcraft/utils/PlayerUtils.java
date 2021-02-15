package net.petersil98.utilcraft.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.common.UsernameCache;
import net.petersil98.utilcraft.data.capabilities.last_death.DefaultLastDeath;
import net.petersil98.utilcraft.data.capabilities.last_death.ILastDeath;
import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PlayerUtils {

    private static Map<String, Integer> playerDeaths = new LinkedHashMap<>();
    private static final ILastDeath lastDeath = new DefaultLastDeath();
    private static boolean veinMinerActive = false;

    public static void setPlayerDeaths(@Nonnull MinecraftServer server, @Nonnull ServerPlayerEntity playerEntity) {
        playerDeaths.put(playerEntity.getName().getString(), playerEntity.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS)));
        List<ServerPlayerEntity> onlinePlayers = server.getPlayerList().getPlayers();
        for(ServerPlayerEntity onlinePlayer: onlinePlayers) {
            int deaths = onlinePlayer.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS));
            playerDeaths.put(onlinePlayer.getName().getString(), deaths);
        }
        try {
            File statsFolder = server.func_240776_a_(FolderName.STATS).toFile();
            if(statsFolder.isDirectory() && statsFolder.listFiles() != null && statsFolder.listFiles().length > 0) {
                for (File playerStatFile : statsFolder.listFiles()) {
                    String uuid = FilenameUtils.removeExtension(playerStatFile.getName());
                    String username = UsernameCache.getLastKnownUsername(UUID.fromString(uuid));
                    if(!playerDeaths.containsKey(username)) {
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(new FileReader(playerStatFile));
                        JsonObject object = element.getAsJsonObject();
                        JsonObject stats = object.getAsJsonObject("stats");
                        JsonObject customStats = stats.getAsJsonObject(Stats.CUSTOM.getRegistryName().toString());
                        JsonPrimitive deaths = customStats.getAsJsonPrimitive(Stats.DEATHS.toString());
                        playerDeaths.put(username, deaths == null ? 0 : deaths.getAsInt());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sort();
    }

    public static void setPlayerDeaths(Map<String, Integer> map) {
        playerDeaths = map;
    }

    public static Map<String, Integer> getPlayerDeaths() {
        return playerDeaths;
    }

    private static void sort() {
        playerDeaths = playerDeaths.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void setLastDeath(BlockPos deathPoint, ResourceLocation dimension) {
        lastDeath.setDeathPoint(deathPoint);
        lastDeath.setDeathDimension(dimension);
    }

    public static ILastDeath getLastDeath() {
        return lastDeath;
    }

    public static boolean isVeinMinerActive() {
        return veinMinerActive;
    }

    public static void setVeinMinerActive(boolean isVeinMinerActive) {
        PlayerUtils.veinMinerActive = isVeinMinerActive;
    }
}
