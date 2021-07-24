package net.petersil98.utilcraft.data;

import java.util.UUID;

public record SimplePlayer(String username, UUID uuid) {

    public String getUsername() {
        return username;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass() && ((SimplePlayer) o).getUUID() == getUUID();
    }
}
