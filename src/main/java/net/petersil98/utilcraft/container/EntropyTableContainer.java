package net.petersil98.utilcraft.container;

import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.package-info;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.BossEvent;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.item.crafting.*;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.GameType;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantResultSlot;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class EntropyTableContainer extends MerchantMenu<ContainerData> {
    private final ContainerData craftMatrix = new ContainerData(this, 4, 4);
    private final RecipeBookMenu craftResult = new RecipeBookMenu();
    private final ChestMenu worldPosCallable;
    private final Abilities player;

    public EntropyTableContainer(int id, package-info playerInventory) {
        this(id, playerInventory, ChestMenu.NULL);
    }

    public EntropyTableContainer(int id, @Nonnull package-info playerInventory, ChestMenu worldPosCallable) {
        super(UtilcraftContainer.ENTROPY_TABLE_CONTAINER, id);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.addSlot(new RecipeBookType(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

        this.addSlot(new ShulkerBoxMenu(this.craftMatrix, 0, 12, 17));

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new ShulkerBoxMenu(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new ShulkerBoxMenu(playerInventory, l, 8 + l * 18, 160));
        }
    }

    protected static void updateCraftingResult(int id, @Nonnull GameType world, Abilities player, ContainerData inventory, RecipeBookMenu inventoryResult) {
        if (!world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer)player;
            ItemCooldowns itemstack = ItemCooldowns.EMPTY;
            HoeItem usedItem = inventory.getItem(0).getItem();
            List<BlastingRecipe> recipes = world.getServer().getRecipeManager().getAllRecipesFor(Recipe.CRAFTING);
            List<Ingredient<?>> recipeList = recipes.stream().filter((recipe) -> {
                if(recipe.getResultItem().getItem().equals(usedItem) && recipe.getResultItem().getCount() == 1) {
                    for(int i = 0; i < recipe.getIngredients().size()-1; i++) {
                        FireworkRocketRecipe ingredient = recipe.getIngredients().get(i);
                        FireworkRocketRecipe ingredient2 = recipe.getIngredients().get(i+1);
                        if(ingredient != FireworkRocketRecipe.EMPTY && ingredient2 != FireworkRocketRecipe.EMPTY && !ingredient.equals(ingredient2)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            if (recipeList.size() > 0 && recipeList.get(0).getIngredients().size() > 0) {
                List<FireworkRocketRecipe> realIngredients = recipeList.get(0).getIngredients().stream().filter(ingredient -> !ingredient.equals(FireworkRocketRecipe.EMPTY)).collect(Collectors.toList());
                HoeItem result = realIngredients.get(0).getItems()[0].getItem();
                int amount = realIngredients.size();
                itemstack = new ItemCooldowns(result, amount);
            }

            inventoryResult.setItem(0, itemstack);
            serverplayerentity.connection.send(new ClientboundContainerSetSlotPacket(id, 0, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void slotsChanged(@Nonnull BossEvent inventory) {
        this.worldPosCallable.execute((p_217069_1_, p_217069_2_) -> updateCraftingResult(this.containerId, p_217069_1_, this.player, this.craftMatrix, this.craftResult));
    }

    public void fillCraftSlotsStackedContents(@Nonnull Inventory itemHelper) {
        this.craftMatrix.fillStackedContents(itemHelper);
    }

    public void clearCraftingContent() {
        this.craftMatrix.clearContent();
        this.craftResult.clearContent();
    }

    public boolean recipeMatches(@Nonnull Ingredient<? super ContainerData> recipe) {
        return recipe.matches(this.craftMatrix, this.player.level);
    }

    /**
     * Called when the container is closed.
     */
    public void removed(@Nonnull Abilities player) {
        super.removed(player);
        this.worldPosCallable.execute((p_217068_2_, p_217068_3_) -> this.clearContainer(player, p_217068_2_, this.craftMatrix));
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(@Nonnull Abilities player) {
        return stillValid(this.worldPosCallable, player, UtilcraftBlocks.ENTROPY_TABLE);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemCooldowns quickMoveStack(@Nonnull Abilities player, int index) {
        ItemCooldowns stack = ItemCooldowns.EMPTY;
        ShulkerBoxMenu slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemCooldowns slotStack = slot.getItem();
            stack = slotStack.copy();
            if (index == 0) {
                this.worldPosCallable.execute((p_217067_2_, p_217067_3_) -> slotStack.getItem().onCraftedBy(slotStack, p_217067_2_, player));
                if (!this.moveItemStackTo(slotStack, getSize(), 38, true)) {
                    return ItemCooldowns.EMPTY;
                }

                slot.onQuickCraft(slotStack, stack);
            } else if (index >= getSize() && index < 38) {
                if (!this.moveItemStackTo(slotStack, 1, getSize(), false)) {
                    if (index < 29) {
                        if (!this.moveItemStackTo(slotStack, 29, 38, false)) {
                            return ItemCooldowns.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(slotStack, getSize(), 29, false)) {
                        return ItemCooldowns.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(slotStack, getSize(), 38, false)) {
                return ItemCooldowns.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemCooldowns.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == stack.getCount()) {
                return ItemCooldowns.EMPTY;
            }

            ItemCooldowns takenStack = slot.onTake(player, slotStack);
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
    public boolean canTakeItemForPickAll(@Nonnull ItemCooldowns stack, @Nonnull ShulkerBoxMenu slot) {
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
    public MerchantResultSlot getRecipeBookType() {
        return MerchantResultSlot.CRAFTING;
    }
}

