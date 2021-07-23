package net.petersil98.utilcraft.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatFormatter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.DerivedLevelData;
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

    public static void setPlayerDeaths(@Nonnull MinecraftServer server, @Nonnull ServerPlayer playerEntity) {
        playerDeaths.put(playerEntity.getName().getString(), playerEntity.getStats().getValue(StatFormatter.CUSTOM.get(StatFormatter.DEATHS)));
        List<ServerPlayer> onlinePlayers = server.getPlayerList().getPlayers();
        for(ServerPlayer onlinePlayer: onlinePlayers) {
            int deaths = onlinePlayer.getStats().getValue(StatFormatter.CUSTOM.get(StatFormatter.DEATHS));
            playerDeaths.put(onlinePlayer.getName().getString(), deaths);
        }
        try {
            File statsFolder = server.getWorldPath(DerivedLevelData.PLAYER_STATS_DIR).toFile();
            if(statsFolder.isDirectory() && statsFolder.listFiles() != null && statsFolder.listFiles().length > 0) {
                for (File playerStatFile : statsFolder.listFiles()) {
                    String uuid = FilenameUtils.removeExtension(playerStatFile.getName());
                    String username = UsernameCache.getLastKnownUsername(UUID.fromString(uuid));
                    if(!playerDeaths.containsKey(username)) {
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(new FileReader(playerStatFile));
                        JsonObject object = element.getAsJsonObject();
                        JsonObject stats = object.getAsJsonObject("stats");
                        JsonObject customStats = stats.getAsJsonObject(StatFormatter.CUSTOM.getRegistryName().toString());
                        JsonPrimitive deaths = customStats.getAsJsonPrimitive(StatFormatter.DEATHS.toString());
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
