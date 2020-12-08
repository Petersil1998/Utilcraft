package net.petersil98.utilcraft.data.capabilities.inventory;

import net.minecraftforge.items.IItemHandler;

public interface IInventory {

    void setInventory(IItemHandler inventory);

    IItemHandler getInventory();
}
