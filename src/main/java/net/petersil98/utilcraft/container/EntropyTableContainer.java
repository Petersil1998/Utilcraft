package net.petersil98.utilcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class EntropyTableContainer extends RecipeBookContainer<CraftingInventory> {
    private final CraftingInventory craftMatrix = new CraftingInventory(this, 4, 4);
    private final CraftResultInventory craftResult = new CraftResultInventory();
    private final IWorldPosCallable worldPosCallable;
    private final PlayerEntity player;

    public EntropyTableContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, IWorldPosCallable.NULL);
    }

    public EntropyTableContainer(int id, @Nonnull PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(UtilcraftContainer.ENTROPY_TABLE_CONTAINER.get(), id);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

        this.addSlot(new Slot(this.craftMatrix, 0, 12, 17));

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 160));
        }
    }

    protected static void updateCraftingResult(int id, @Nonnull World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory inventoryResult) {
        if (!world.isClientSide) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Item usedItem = inventory.getItem(0).getItem();
            List<ICraftingRecipe> recipes = world.getServer().getRecipeManager().getAllRecipesFor(IRecipeType.CRAFTING);
            List<IRecipe<?>> recipeList = recipes.stream().filter((recipe) -> {
                if(recipe.getResultItem().getItem().equals(usedItem) && recipe.getResultItem().getCount() == 1) {
                    for(int i = 0; i < recipe.getIngredients().size()-1; i++) {
                        Ingredient ingredient = recipe.getIngredients().get(i);
                        Ingredient ingredient2 = recipe.getIngredients().get(i+1);
                        if(ingredient != Ingredient.EMPTY && ingredient2 != Ingredient.EMPTY && !ingredient.equals(ingredient2)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (recipeList.size() > 0 && recipeList.get(0).getIngredients().size() > 0) {
                List<Ingredient> realIngredients = recipeList.get(0).getIngredients().stream().filter(ingredient -> !ingredient.equals(Ingredient.EMPTY)).collect(Collectors.toList());
                Item result = realIngredients.get(0).getItems()[0].getItem();
                int amount = realIngredients.size();
                itemstack = new ItemStack(result, amount);
            }

            inventoryResult.setItem(0, itemstack);
            serverplayerentity.connection.send(new SSetSlotPacket(id, 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(@Nonnull IInventory inventory) {
        this.worldPosCallable.execute((p_217069_1_, p_217069_2_) -> updateCraftingResult(this.containerId, p_217069_1_, this.player, this.craftMatrix, this.craftResult));
    }

    public void fillCraftSlotsStackedContents(@Nonnull RecipeItemHelper itemHelper) {
        this.craftMatrix.fillStackedContents(itemHelper);
    }

    public void clearCraftingContent() {
        this.craftMatrix.clearContent();
        this.craftResult.clearContent();
    }

    public boolean recipeMatches(@Nonnull IRecipe<? super CraftingInventory> recipe) {
        return recipe.matches(this.craftMatrix, this.player.level);
    }

    /**
     * Called when the container is closed.
     */
    public void removed(@Nonnull PlayerEntity player) {
        super.removed(player);
        this.worldPosCallable.execute((p_217068_2_, p_217068_3_) -> this.clearContainer(player, p_217068_2_, this.craftMatrix));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return stillValid(this.worldPosCallable, player, UtilcraftBlocks.ENTROPY_TABLE.get());
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemStack quickMoveStack(@Nonnull PlayerEntity player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            stack = slotStack.copy();
            if (index == 0) {
                this.worldPosCallable.execute((p_217067_2_, p_217067_3_) -> slotStack.getItem().onCraftedBy(slotStack, p_217067_2_, player));
                if (!this.moveItemStackTo(slotStack, getSize(), 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(slotStack, stack);
            } else if (index >= getSize() && index < 38) {
                if (!this.moveItemStackTo(slotStack, 1, getSize(), false)) {
                    if (index < 29) {
                        if (!this.moveItemStackTo(slotStack, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(slotStack, getSize(), 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(slotStack, getSize(), 38, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack takenStack = slot.onTake(player, slotStack);
            if (index == 0) {
                player.drop(takenStack, false);
            }
        }

        return stack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
     * null for the initial slot that was double-clicked.
     */
    public boolean canTakeItemForPickAll(@Nonnull ItemStack stack, @Nonnull Slot slot) {
        return slot.container != this.craftResult && super.canTakeItemForPickAll(stack, slot);
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.craftMatrix.getWidth();
    }

    public int getGridHeight() {
        return this.craftMatrix.getHeight();
    }

    public int getSize() {
        return 2;
    }

    @Nonnull
    public RecipeBookCategory getRecipeBookType() {
        return RecipeBookCategory.CRAFTING;
    }
}

