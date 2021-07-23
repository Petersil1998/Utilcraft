package net.petersil98.utilcraft.data.capabilities.vein_miner;

public class DefaultVeinMiner implements IVeinMiner {

    private boolean active;

    @Override
    public void setVeinMiner(boolean active) {
        this.active = active;
    }

    public boolean getVeinMiner() {
        return active;
    }
}
