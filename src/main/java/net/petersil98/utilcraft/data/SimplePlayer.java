package net.petersil98.utilcraft.data;

import java.util.UUID;

public class SimplePlayer {

    private final String username;
    private final UUID uuid;

    public SimplePlayer(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass() && ((SimplePlayer)o).getUUID() == getUUID();
    }
}
