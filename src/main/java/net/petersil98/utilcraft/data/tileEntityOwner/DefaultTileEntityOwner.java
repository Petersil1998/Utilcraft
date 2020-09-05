package net.petersil98.utilcraft.data.tileEntityOwner;

import java.util.UUID;

public class DefaultTileEntityOwner implements ITileEntityOwner {

    private UUID owner;

    @Override
    public void setOwner(UUID player) {
        this.owner = player;
    }

    @Override
    public UUID getOwner() {
        return this.owner;
    }
}
