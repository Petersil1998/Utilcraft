package net.petersil98.utilcraft.data;

import com.google.common.collect.Lists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.common.util.WorldCapabilityData;
import net.petersil98.utilcraft.Utilcraft;

import javax.annotation.Nonnull;
import java.util.*;

public class UtilcraftWorldSavedData extends SavedData {

    private static final String DATA_NAME = Utilcraft.MOD_ID;
    private final Map<UUID, List<SimplePlayer>> players = new HashMap<>();

    public UtilcraftWorldSavedData() {}

    public static UtilcraftWorldSavedData load(@Nonnull CompoundTag tag) {
        UtilcraftWorldSavedData data = new UtilcraftWorldSavedData();
        data.read(tag);
        return data;
    }

    public void read(CompoundTag tag) {
        try {
            ListTag players = (ListTag)tag.get("players");
            for (Tag playerTag: players){
                CompoundTag player = (CompoundTag)playerTag;
                UUID playerUUID = player.getUUID("player");
                ListTag trustedPlayers = (ListTag)player.get("trustedPlayers");
                List<SimplePlayer> trustedPlayersList = new ArrayList<>();
                for(Tag trustedPlayerTag: trustedPlayers){
                    UUID trustedPlayer = ((CompoundTag)trustedPlayerTag).getUUID("trustedPlayer");
                    String trustedPlayerName = ((CompoundTag)trustedPlayerTag).getString("trustedPlayerName");
                    trustedPlayersList.add(new SimplePlayer(trustedPlayerName, trustedPlayer));
                }
                this.players.put(playerUUID, trustedPlayersList);
            }
        } catch (Exception ignored){
        }
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag compound) {
        Iterator<Map.Entry<UUID, List<SimplePlayer>>> iterator = this.players.entrySet().iterator();
        ListTag players = new ListTag();
        while (iterator.hasNext()) {
            Map.Entry<UUID, List<SimplePlayer>> pair = iterator.next();
            UUID player = pair.getKey();
            ListTag trustedPlayers = new ListTag();
            Iterator<SimplePlayer> trustedPlayerIterator = pair.getValue().iterator();
            while (trustedPlayerIterator.hasNext()){
                SimplePlayer trustedPlayer = trustedPlayerIterator.next();
                CompoundTag tag = new CompoundTag();
                tag.putUUID("trustedPlayer", trustedPlayer.getUUID());
                tag.putString("trustedPlayerName", trustedPlayer.getUsername());
                trustedPlayers.add(tag);
                trustedPlayerIterator.remove();
            }
            CompoundTag tag = new CompoundTag();
            tag.putUUID("player", player);
            tag.put("trustedPlayers", trustedPlayers);
            players.add(tag);
            iterator.remove();
        }
        compound.put("players", players);
        return compound;
    }

    @Nonnull
    public static UtilcraftWorldSavedData get(@Nonnull ServerLevel world) {
        DimensionDataStorage dataManager = world.getDataStorage();
        return dataManager.computeIfAbsent(UtilcraftWorldSavedData::load, UtilcraftWorldSavedData::new, DATA_NAME);
    }

    public void addTrustedPlayer(UUID playerUUID, SimplePlayer trustedPlayer){
        if(players.containsKey(playerUUID)){
            if(players.get(playerUUID).stream().noneMatch(simplePlayer -> simplePlayer.getUUID() == trustedPlayer.getUUID())) {
                players.get(playerUUID).add(trustedPlayer);
            }
        } else {
            players.put(playerUUID, Lists.newArrayList(trustedPlayer));
        }
        setDirty();
    }

    public void removedTrustedPlayer(UUID playerUUID, UUID trustedPlayerUUID){
        if(players.containsKey(playerUUID)){
            players.get(playerUUID).forEach(simplePlayer -> {
                if (simplePlayer.getUUID() == trustedPlayerUUID) {
                    players.get(playerUUID).remove(simplePlayer);
                }
            });
        }
        setDirty();
    }

    public List<SimplePlayer> getTrustedPlayers(UUID player) {
        return this.players.containsKey(player) ? this.players.get(player) : Lists.newArrayList();
    }
}
