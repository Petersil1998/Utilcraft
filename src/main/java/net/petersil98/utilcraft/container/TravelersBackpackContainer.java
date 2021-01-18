package net.petersil98.utilcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
        addSlots(playerInventory);
    }

    protected void addSlots(PlayerInventory playerInventory)
    {
        int offset = (this.numRows - 4) * 18;

        for(int i = 0; i < this.numRows; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new SlotItemHandler(inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 103 + i * 18 + offset));
            }
        }

        for(int i = 0; i < 9; ++i) {
            if(i == slotNumber)
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161 + offset){
                    @Override
                    public boolean canTakeStack(@Nonnull PlayerEntity playerIn) {
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
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return playerIn.inventory.getStackInSlot(slotNumber).getItem() instanceof TravelersBackpack;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.numRows * 9) {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(@Nonnull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
    }

    @OnlyIn(Dist.CLIENT)
    public int getNumRows() {
        return this.numRows;
    }
}
