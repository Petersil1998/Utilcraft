package net.petersil98.utilcraft.container;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ModItemStackHandler extends ItemStackHandler {

    public ModItemStackHandler(int slots)
    {
        super(slots + 3);
    }

    /**
     * Ignores slot limitations
     */
    public ItemStack extractItem(int slot, int amount)
    {
        return super.extractItem(slot, amount, false);
    }

    /**
     * Ignores slot limitations
     */
    public ItemStack insertItem(int slot, ItemStack stack)
    {
        return super.insertItem(slot, stack, false);
    }

    public boolean isInputEmpty()
    {
        for (int i = 0; i < (this.stacks.size() - 3) / 2; i++)
        {
            if (!this.stacks.get(i).isEmpty())
            {
                return false;
            }
        }
        return true;
    }
}