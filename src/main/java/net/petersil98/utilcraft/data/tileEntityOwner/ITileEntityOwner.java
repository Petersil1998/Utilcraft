package net.petersil98.utilcraft.data.tileEntityOwner;

import net.minecraft.entity.player.PlayerEntity;

public interface ITileEntityOwner {

    void setOwner(PlayerEntity player);

    PlayerEntity getOwner();
}
