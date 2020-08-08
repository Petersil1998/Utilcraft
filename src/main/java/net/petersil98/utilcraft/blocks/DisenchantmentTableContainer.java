package net.petersil98.utilcraft.blocks;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nonnull;

public class DisenchantmentTableContainer extends Container {
    private final IInventory outputInventory = new CraftResultInventory();
    private final IInventory inputInventory = new Inventory(2) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void markDirty() {
            super.markDirty();
            DisenchantmentTableContainer.this.onCraftMatrixChanged(this);
        }
    };
    IWorldPosCallable worldPosCallable;

    public DisenchantmentTableContainer(int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.DUMMY);
    }

    public DisenchantmentTableContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(ModContainer.DISENCHANTMENTBLOCKCONTAINER, id);
        this.worldPosCallable = worldPosCallable;
        this.addSlot(new Slot(this.inputInventory, 0, 15, 47) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return EnchantmentHelper.getEnchantments(stack).size() > 0;
            }

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getSlotStackLimit() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inputInventory, 1, 35, 47) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return Items.BOOK.equals(stack.getItem());
            }

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getSlotStackLimit() {
                return 1;
            }
        });

        this.addSlot(new Slot(this.outputInventory, 2, 129, 34) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }

            @Nonnull
            public ItemStack onTake(@Nonnull PlayerEntity thePlayer, @Nonnull ItemStack stack) {
                ItemStack enchantedItem = DisenchantmentTableContainer.this.inputInventory.getStackInSlot(0);
                DisenchantmentTableContainer.this.inputInventory.setInventorySlotContents(0, removeEnchantments(enchantedItem));
                DisenchantmentTableContainer.this.inputInventory.setInventorySlotContents(1, ItemStack.EMPTY);
                return stack;
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(@Nonnull IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        if (inventoryIn == this.inputInventory) {
            this.updateRecipeOutput();
        }

    }

    private void updateRecipeOutput() {
        ItemStack enchantedItem = this.inputInventory.getStackInSlot(0);
        ItemStack book = this.inputInventory.getStackInSlot(1);
        boolean ready = !enchantedItem.isEmpty() && !book.isEmpty();
        if (!ready) {
            this.outputInventory.setInventorySlotContents(0, ItemStack.EMPTY);
        } else {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(enchantedItem), enchantedBook);
            this.outputInventory.setInventorySlotContents(0, enchantedBook);
        }
        this.detectAndSendChanges();
    }

    public void onContainerClosed(@Nonnull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.worldPosCallable.consume((p_217004_2_, p_217004_3_) -> this.clearContainer(playerIn, playerIn.world, this.inputInventory));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, ModBlocks.DISENCHANTMENTTABLE);
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
            if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.getItem() == Items.BOOK) {
                if (!this.mergeItemStack(itemstack1, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.inventorySlots.get(0).getHasStack() || !this.inventorySlots.get(0).isItemValid(itemstack1)) {
                    return ItemStack.EMPTY;
                }

                ItemStack itemstack2 = itemstack1.copy();
                itemstack2.setCount(1);
                itemstack1.shrink(1);
                this.inventorySlots.get(0).putStack(itemstack2);
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    /**
     * Removes all enchantments from the ItemStack. Note that the curses are not removed.
     */
    private ItemStack removeEnchantments(ItemStack stack) {
        ItemStack itemstack = stack.copy();
        itemstack.removeChildTag("Enchantments");
        itemstack.removeChildTag("StoredEnchantments");
        if (stack.getDamage() > 0) {
            itemstack.setDamage(stack.getDamage());
        } else {
            itemstack.removeChildTag("Damage");
        }

        itemstack.setCount(stack.getCount());
        itemstack.setRepairCost(0);
        if (stack.hasDisplayName()) {
            itemstack.setDisplayName(stack.getDisplayName());
        }

        return itemstack;
    }
}
