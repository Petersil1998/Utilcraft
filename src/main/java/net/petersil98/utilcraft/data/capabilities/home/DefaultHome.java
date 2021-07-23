package net.petersil98.utilcraft.data.capabilities.home;

import net.minecraft.core.BlockPos;

public class DefaultHome implements IHome {

    private BlockPos home;

    @Override
    public void setHome(BlockPos home) {
        this.home = home;
    }

    public BlockPos getHome() {
        return home;
    }
}
