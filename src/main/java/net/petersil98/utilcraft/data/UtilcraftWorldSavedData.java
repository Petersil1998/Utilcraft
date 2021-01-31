package net.petersil98.utilcraft.data;

import com.google.common.collect.Lists;
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
    private final Map<UUID, List<SimplePlayer>> players = new HashMap<>();

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
                List<SimplePlayer> trustedPlayersList = new ArrayList<>();
                for(INBT trustedPlayerTag: trustedPlayers){
                    UUID trustedPlayer = ((CompoundNBT)trustedPlayerTag).getUniqueId("trustedPlayer");
                    String trustedPlayerName = ((CompoundNBT)trustedPlayerTag).getString("trustedPlayerName");
                    trustedPlayersList.add(new SimplePlayer(trustedPlayerName, trustedPlayer));
                }
                this.players.put(playerUUID, trustedPlayersList);
            }
        } catch (Exception ignored){
        }
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        Iterator<Map.Entry<UUID, List<SimplePlayer>>> iterator = players.entrySet().iterator();
        ListNBT players = new ListNBT();
        while (iterator.hasNext()) {
            Map.Entry<UUID, List<SimplePlayer>> pair = iterator.next();
            UUID player = pair.getKey();
            ListNBT trustedPlayers = new ListNBT();
            Iterator<SimplePlayer> trustedPlayerIterator = pair.getValue().iterator();
            while (trustedPlayerIterator.hasNext()){
                SimplePlayer trustedPlayer = trustedPlayerIterator.next();
                CompoundNBT tag = new CompoundNBT();
                tag.putUniqueId("trustedPlayer", trustedPlayer.getUUID());
                tag.putString("trustedPlayerName", trustedPlayer.getUsername());
                trustedPlayers.add(tag);
                trustedPlayerIterator.remove();
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

    public void addTrustedPlayer(UUID playerUUID, SimplePlayer trustedPlayer){
        if(players.containsKey(playerUUID)){
            if(players.get(playerUUID).stream().noneMatch(simplePlayer -> simplePlayer.getUUID() == trustedPlayer.getUUID())) {
                players.get(playerUUID).add(trustedPlayer);
            }
        } else {
            players.put(playerUUID, Lists.newArrayList(trustedPlayer));
        }
        markDirty();
    }

    public void removedTrustedPlayer(UUID playerUUID, UUID trustedPlayerUUID){
        if(players.containsKey(playerUUID)){
            players.get(playerUUID).forEach(simplePlayer -> {
                if (simplePlayer.getUUID() == trustedPlayerUUID) {
                    players.get(playerUUID).remove(simplePlayer);
                }
            });
        }
        markDirty();
    }

    public List<SimplePlayer> getTrustedPlayers(UUID player) {
        return players.containsKey(player) ? players.get(player) : Lists.newArrayList();
    }
}
