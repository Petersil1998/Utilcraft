package net.petersil98.utilcraft.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.*;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.level.Level;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class EntropyTableContainer extends RecipeBookMenu<CraftingContainer> {
    private final CraftingContainer craftMatrix = new CraftingContainer(this, 4, 4);
    private final ResultContainer craftResult = new ResultContainer();
    private final ContainerLevelAccess worldPosCallable;
    private final Player player;

    public EntropyTableContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.NULL);
    }

    public EntropyTableContainer(int id, @Nonnull Inventory playerInventory, ContainerLevelAccess worldPosCallable) {
        super(UtilcraftContainer.ENTROPY_TABLE_CONTAINER, id);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.addSlot(new ResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

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

    protected static void updateCraftingResult(AbstractContainerMenu container, @Nonnull Level world, Player player, CraftingContainer inventory, ResultContainer inventoryResult) {
        if (!world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Item usedItem = inventory.getItem(0).getItem();
            List<CraftingRecipe> recipes = world.getServer().getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING);
            List<Recipe<?>> recipeList = recipes.stream().filter((recipe) -> {
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
            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(container.containerId, container.incrementStateId(), 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(@Nonnull Container inventory) {
        this.worldPosCallable.execute((p_217069_1_, p_217069_2_) -> updateCraftingResult(this, p_217069_1_, this.player, this.craftMatrix, this.craftResult));
    }

    public void fillCraftSlotsStackedContents(@Nonnull StackedContents itemHelper) {
        this.craftMatrix.fillStackedContents(itemHelper);
    }

    public void clearCraftingContent() {
        this.craftMatrix.clearContent();
        this.craftResult.clearContent();
    }

    public boolean recipeMatches(@Nonnull Recipe<? super CraftingContainer> recipe) {
        return recipe.matches(this.craftMatrix, this.player.level);
    }

    /**
     * Called when the container is closed.
     */
    public void removed(@Nonnull Player player) {
        super.removed(player);
        this.worldPosCallable.execute((p_217068_2_, p_217068_3_) -> this.clearContainer(player, this.craftMatrix));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(@Nonnull Player player) {
        return stillValid(this.worldPosCallable, player, UtilcraftBlocks.ENTROPY_TABLE);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
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

            slot.onTake(player, slotStack);
            if (index == 0) {
                player.drop(slotStack, false);
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
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int index) {
        return index != this.getResultSlotIndex();
    }
}

