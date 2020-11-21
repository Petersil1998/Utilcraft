package net.petersil98.utilcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class SecureChestContainer extends Container {

    private final IInventory lowerChestInventory;
    private final int numRows;

    public SecureChestContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(9 * 3));
    }

    public SecureChestContainer(int id, PlayerInventory playerInventory, Inventory inventory) {
        super(ModContainer.SECURE_CHEST_CONTAINER, id);
        this.lowerChestInventory = inventory;
        this.numRows = 3;
        inventory.openInventory(playerInventory.player);
        int i = (this.numRows - 4) * 18;

        for(int j = 0; j < this.numRows; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }

    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return this.lowerChestInventory.isUsableByPlayer(playerIn);
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
        this.lowerChestInventory.closeInventory(playerIn);
    }

    public IInventory getLowerChestInventory() {
        return this.lowerChestInventory;
    }

    @OnlyIn(Dist.CLIENT)
    public int getNumRows() {
        return this.numRows;
    }
}
