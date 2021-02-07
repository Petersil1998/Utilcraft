package net.petersil98.utilcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.recipes.UtilcraftRecipeTypes;
import net.petersil98.utilcraft.recipes.SushiMakerRecipe;

import javax.annotation.Nonnull;
import java.util.Optional;

public class SushiMakerContainer extends RecipeBookContainer<CraftingInventory> {
    private final CraftingInventory craftMatrix = new CraftingInventory(this, 4, 4);
    private final CraftResultInventory craftResult = new CraftResultInventory();
    private final IWorldPosCallable worldPosCallable;
    private final PlayerEntity player;

    public SushiMakerContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, IWorldPosCallable.DUMMY);
    }

    public SushiMakerContainer(int id, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(UtilcraftContainer.SUSHI_MAKER_CONTAINER, id);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.addSlot(new Slot(this.craftMatrix, j + i * 4, 12 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 160));
        }
    }

    protected static void updateCraftingResult(int id, World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory inventoryResult) {
        if (!world.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<SushiMakerRecipe> optional = world.getServer().getRecipeManager().getRecipe(UtilcraftRecipeTypes.SUSHI_MAKER_RECIPE, inventory, world);
            if (optional.isPresent()) {
                SushiMakerRecipe icraftingrecipe = optional.get();
                if (inventoryResult.canUseRecipe(world, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.getCraftingResult(inventory);
                }
            }

            inventoryResult.setInventorySlotContents(0, itemstack);
            serverplayerentity.connection.sendPacket(new SSetSlotPacket(id, 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(@Nonnull IInventory inventoryIn) {
        this.worldPosCallable.consume((p_217069_1_, p_217069_2_) -> updateCraftingResult(this.windowId, p_217069_1_, this.player, this.craftMatrix, this.craftResult));
    }

    public void fillStackedContents(@Nonnull RecipeItemHelper itemHelperIn) {
        this.craftMatrix.fillStackedContents(itemHelperIn);
    }

    public void clear() {
        this.craftMatrix.clear();
        this.craftResult.clear();
    }

    public boolean matches(IRecipe<? super CraftingInventory> recipeIn) {
        return recipeIn.matches(this.craftMatrix, this.player.world);
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(@Nonnull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.worldPosCallable.consume((p_217068_2_, p_217068_3_) -> this.clearContainer(playerIn, p_217068_2_, this.craftMatrix));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return isWithinUsableDistance(this.worldPosCallable, playerIn, UtilcraftBlocks.SUSHI_MAKER);
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
                this.worldPosCallable.consume((p_217067_2_, p_217067_3_) -> itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn));
                if (!this.mergeItemStack(itemstack1, getSize(), 53, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= getSize() && index < 53) {
                if (!this.mergeItemStack(itemstack1, 1, getSize(), false)) {
                    if (index < 44) {
                        if (!this.mergeItemStack(itemstack1, 44, 53, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(itemstack1, getSize(), 44, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, getSize(), 53, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
     * null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(@Nonnull ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }

    public int getOutputSlot() {
        return 0;
    }

    public int getWidth() {
        return this.craftMatrix.getWidth();
    }

    public int getHeight() {
        return this.craftMatrix.getHeight();
    }

    public int getSize() {
        return 17;
    }

    @Nonnull
    public RecipeBookCategory func_241850_m() {
        return RecipeBookCategory.CRAFTING;
    }
}
