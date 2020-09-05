package net.petersil98.utilcraft.data.trustedPlayers;

import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public interface ITrustedPlayers {

    void setTrustedPlayers(List<PlayerEntity> players);

    List<PlayerEntity> getTrustedPlayers();

    void addTrustedPlayer(PlayerEntity player);

    void removeTrustedPlayer(PlayerEntity player);
}
