package net.petersil98.utilcraft.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.petersil98.utilcraft.Utilcraft;

import javax.annotation.Nonnull;
import java.util.*;

public class UtilcraftWorldSavedData extends WorldSavedData {

    private static final String DATA_NAME = Utilcraft.MOD_ID;
    private final Map<UUID, Map<UUID, String>> players = new HashMap<>();

    public UtilcraftWorldSavedData() {
        this(DATA_NAME);
    }
    public UtilcraftWorldSavedData(String name) {
        super(name);
    }

    @Override
    public void read(@Nonnull CompoundNBT nbt) {
        try {
            ListNBT players = (ListNBT)nbt.get("players");
            for (INBT playerTag: players){
                CompoundNBT player = (CompoundNBT)playerTag;
                UUID playerUUID = player.getUniqueId("player");
                ListNBT trustedPlayers = (ListNBT)player.get("trustedPlayers");
                Map<UUID, String> trustedPlayersList = new HashMap<>();
                for(INBT trustedPlayerTag: trustedPlayers){
                    UUID trustedPlayer = ((CompoundNBT)trustedPlayerTag).getUniqueId("trustedPlayer");
                    String trustedPlayerName = ((CompoundNBT)trustedPlayerTag).getString("trustedPlayerName");
                    trustedPlayersList.put(trustedPlayer, trustedPlayerName);
                }
                this.players.put(playerUUID, trustedPlayersList);
            }
        } catch (Exception ignored){
        }
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        Iterator<Map.Entry<UUID, Map<UUID, String>>> iterator = players.entrySet().iterator();
        ListNBT players = new ListNBT();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Map<UUID, String>> pair = iterator.next();
            UUID player = pair.getKey();
            ListNBT trustedPlayers = new ListNBT();
            Iterator<Map.Entry<UUID, String>> iterator2 = pair.getValue().entrySet().iterator();
            while (iterator2.hasNext()){
                Map.Entry<UUID, String> trustedPlayer = iterator2.next();
                CompoundNBT tag = new CompoundNBT();
                tag.putUniqueId("trustedPlayer", trustedPlayer.getKey());
                tag.putString("trustedPlayerName", trustedPlayer.getValue());
                trustedPlayers.add(tag);
                iterator2.remove();
            }
            CompoundNBT tag = new CompoundNBT();
            tag.putUniqueId("player", player);
            tag.put("trustedPlayers", trustedPlayers);
            players.add(tag);
            iterator.remove();
        }
        compound.put("players", players);
        return compound;
    }

    public static UtilcraftWorldSavedData get(ServerWorld world) {
        DimensionSavedDataManager dataManager = world.getSavedData();
        return dataManager.getOrCreate(UtilcraftWorldSavedData::new, DATA_NAME);
    }

    public void addTrustedPlayer(UUID playerUUID, UUID trustedPlayerUUID, String trustedPlayerName){
        if(players.containsKey(playerUUID)){
            players.get(playerUUID).put(trustedPlayerUUID, trustedPlayerName);
        } else {
            players.put(playerUUID, Maps.newHashMap(
                    ImmutableMap.of(trustedPlayerUUID, trustedPlayerName)
            ));
        }
        markDirty();
    }

    public void removedTrustedPlayer(UUID playerUUID, UUID trustedPlayerUUID){
        if(players.containsKey(playerUUID)){
            players.get(playerUUID).remove(trustedPlayerUUID);
        }
        markDirty();
    }

    public List<String> getTrustedPlayerNames(UUID playerUUID){
        if(players.containsKey(playerUUID)){
            return new ArrayList<>(players.get(playerUUID).values());
        } else {
            return Lists.newArrayList();
        }
    }

    public List<UUID> getTrustedPlayerUUIDs(UUID playerUUID){
        if(players.containsKey(playerUUID)){
            return new ArrayList<>(players.get(playerUUID).keySet());
        } else {
            return Lists.newArrayList();
        }
    }
}
