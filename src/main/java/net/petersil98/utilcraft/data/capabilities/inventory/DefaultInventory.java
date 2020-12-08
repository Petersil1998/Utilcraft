package net.petersil98.utilcraft.data.capabilities.inventory;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class DefaultInventory implements IInventory {

    private IItemHandler inventory;

    public DefaultInventory() {
        this(27);
    }

    public DefaultInventory(int size) {
        setInventory(new ItemStackHandler(size));
    }

    @Override
    public void setInventory(IItemHandler inventory) {
        this.inventory = inventory;
    }

    public IItemHandler getInventory() {
        return inventory;
    }
}
