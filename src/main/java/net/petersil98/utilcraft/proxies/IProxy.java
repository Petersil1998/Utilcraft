package net.petersil98.utilcraft.proxies;

import net.minecraft.world.World;

public interface IProxy {

    void init();

    World getClientWorld();
}
