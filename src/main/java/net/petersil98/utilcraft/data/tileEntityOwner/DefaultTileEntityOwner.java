package net.petersil98.utilcraft.data.tileEntityOwner;

import net.minecraft.entity.player.PlayerEntity;

public class DefaultTileEntityOwner implements ITileEntityOwner {

    private PlayerEntity owner;

    @Override
    public void setOwner(PlayerEntity player) {
        this.owner = player;
    }

    @Override
    public PlayerEntity getOwner() {
        return this.owner;
    }
}
