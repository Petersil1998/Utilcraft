package net.petersil98.utilcraft.container;

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
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;

public class DisenchantmentTableContainer extends Container {
    private final IInventory outputInventory = new CraftResultInventory();
    private final IInventory inputInventory = new Inventory(2) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void setChanged() {
            super.setChanged();
            DisenchantmentTableContainer.this.slotsChanged(this);
        }
    };
    IWorldPosCallable worldPosCallable;

    public DisenchantmentTableContainer(int windowId, PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.NULL);
    }

    public DisenchantmentTableContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(UtilcraftContainer.DISENCHANTMENT_BLOCK_CONTAINER.get(), id);
        this.worldPosCallable = worldPosCallable;
        this.addSlot(new Slot(this.inputInventory, 0, 15, 25) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return EnchantmentHelper.getEnchantments(stack).size() > 0;
            }

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.inputInventory, 1, 35, 25) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return Items.BOOK.equals(stack.getItem());
            }

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getMaxStackSize() {
                return 1;
            }
        });

        this.addSlot(new Slot(this.outputInventory, 2, 145, 25) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(@Nonnull ItemStack stack) {
                return false;
            }

            @Nonnull
            public ItemStack onTake(@Nonnull PlayerEntity thePlayer, @Nonnull ItemStack stack) {
                ItemStack enchantedItem = DisenchantmentTableContainer.this.inputInventory.getItem(0);
                DisenchantmentTableContainer.this.inputInventory.setItem(0, removeEnchantments(enchantedItem));
                DisenchantmentTableContainer.this.inputInventory.setItem(1, ItemStack.EMPTY);
                return stack;
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 62 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 120));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(@Nonnull IInventory inventory) {
        super.slotsChanged(inventory);
        if (inventory == this.inputInventory) {
            this.updateRecipeOutput();
        }

    }

    private void updateRecipeOutput() {
        ItemStack enchantedItem = this.inputInventory.getItem(0);
        ItemStack book = this.inputInventory.getItem(1);
        boolean ready = !enchantedItem.isEmpty() && !book.isEmpty();
        if (!ready) {
            this.outputInventory.setItem(0, ItemStack.EMPTY);
        } else {
            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(enchantedItem), enchantedBook);
            this.outputInventory.setItem(0, enchantedBook);
        }
        this.broadcastChanges();
    }

    public void removed(@Nonnull PlayerEntity player) {
        super.removed(player);
        this.worldPosCallable.execute((p_217004_2_, p_217004_3_) -> this.clearContainer(player, player.level, this.inputInventory));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return stillValid(this.worldPosCallable, player, UtilcraftBlocks.DISENCHANTMENT_TABLE.get());
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
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == 1) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.getItem() == Items.BOOK) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.slots.get(0).hasItem() || !this.slots.get(0).mayPlace(itemstack1)) {
                    return ItemStack.EMPTY;
                }

                ItemStack itemstack2 = itemstack1.copy();
                itemstack2.setCount(1);
                itemstack1.shrink(1);
                this.slots.get(0).set(itemstack2);
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    /**
     * Removes all enchantments from the ItemStack. Note that the curses are not removed.
     */
    @Nonnull
    private ItemStack removeEnchantments(@Nonnull ItemStack stack) {
        ItemStack itemstack = stack.copy();
        itemstack.removeTagKey("Enchantments");
        itemstack.removeTagKey("StoredEnchantments");
        if (stack.getDamageValue() > 0) {
            itemstack.setDamageValue(stack.getDamageValue());
        } else {
            itemstack.removeTagKey("Damage");
        }

        itemstack.setCount(stack.getCount());
        itemstack.setRepairCost(0);
        if (stack.hasCustomHoverName()) {
            itemstack.setHoverName(stack.getHoverName());
        }

        return itemstack;
    }
}
