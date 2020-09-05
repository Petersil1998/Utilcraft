package net.petersil98.utilcraft.data.trustedPlayers;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.petersil98.utilcraft.data.tileEntityOwner.DefaultTileEntityOwner;

import java.util.List;

public class DefaultTrustedPlayers implements ITrustedPlayers {

    private List<PlayerEntity> trustedPlayers;

    public DefaultTrustedPlayers(){
        trustedPlayers = Lists.newArrayList();
    }

    @Override
    public void setTrustedPlayers(List<PlayerEntity> trustedPlayers) {
        this.trustedPlayers = trustedPlayers;
    }

    @Override
    public List<PlayerEntity> getTrustedPlayers() {
        return this.trustedPlayers;
    }

    @Override
    public void addTrustedPlayer(PlayerEntity player) {
        trustedPlayers.add(player);
    }

    @Override
    public void removeTrustedPlayer(PlayerEntity player) {
        trustedPlayers.remove(player);
    }
}
