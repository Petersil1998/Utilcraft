package net.petersil98.utilcraft.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.common.UsernameCache;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PlayerUtils {

    private static Map<String, Integer> playerDeaths = new LinkedHashMap<>();
    private static final int MAX_PLAYERS = 3;

    public static void setPlayerDeaths(MinecraftServer server, ServerPlayerEntity playerEntity) {
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
                        playerDeaths.put(username, deaths.getAsInt());
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
        Map<String, Integer> newMap = playerDeaths.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        playerDeaths.clear();
        int i = 0;
        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            if (i < MAX_PLAYERS) {
                playerDeaths.put(entry.getKey(), entry.getValue());
            } else {
                break;
            }
            i++;
        }
    }
}
