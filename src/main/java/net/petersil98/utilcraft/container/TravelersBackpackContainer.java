package net.petersil98.utilcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.petersil98.utilcraft.items.TravelersBackpack;

import javax.annotation.Nonnull;

public class TravelersBackpackContainer extends Container {

    private final IItemHandler inventory;
    private final int numRows;
    private final int slotNumber;

    public TravelersBackpackContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(id, playerInventory, new ItemStackHandler(buffer.readInt()), buffer.readInt());
    }

    public TravelersBackpackContainer(int id, PlayerInventory playerInventory, IItemHandler inventory, int slotNumber) {
        super(UtilcraftContainer.TRAVELERS_BACKPACK_CONTAINER, id);
        this.inventory = inventory;
        this.numRows = inventory.getSlots()/9;
        this.slotNumber = slotNumber;
        this.addSlots(playerInventory);
    }

    protected void addSlots(PlayerInventory playerInventory)
    {
        int offset = (this.numRows - 4) * 18;

        for(int i = 0; i < this.numRows; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new SlotItemHandler(this.inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 103 + i * 18 + offset));
            }
        }

        for(int i = 0; i < 9; ++i) {
            if(i == this.slotNumber)
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161 + offset){
                    @Override
                    public boolean mayPickup(@Nonnull PlayerEntity player) {
                        return false;
                    }
                });
            else
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161 + offset));
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return player.inventory.getItem(this.slotNumber).getItem() instanceof TravelersBackpack;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemStack quickMoveStack(@Nonnull PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();
            if (index < this.numRows * 9) {
                if (!this.moveItemStackTo(slotStack, this.numRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, this.numRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void removed(@Nonnull PlayerEntity player) {
        super.removed(player);
    }

    public int getNumRows() {
        return this.numRows;
    }
}
