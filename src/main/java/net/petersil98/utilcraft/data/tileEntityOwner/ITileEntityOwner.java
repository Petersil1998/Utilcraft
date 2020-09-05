package net.petersil98.utilcraft.data.tileEntityOwner;

import java.util.UUID;

public interface ITileEntityOwner {

    void setOwner(UUID player);

    UUID getOwner();
}
