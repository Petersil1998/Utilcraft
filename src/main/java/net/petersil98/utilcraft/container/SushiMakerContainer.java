package net.petersil98.utilcraft.container;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.recipes.SushiMakerRecipe;
import net.petersil98.utilcraft.recipes.UtilcraftRecipeTypes;

import javax.annotation.Nonnull;
import java.util.Optional;

public class SushiMakerContainer extends RecipeBookMenu<CraftingContainer> {
    private final CraftingContainer craftMatrix = new CraftingContainer(this, 4, 4);
    private final ResultContainer craftResult = new ResultContainer();
    private final ContainerLevelAccess worldPosCallable;
    private final Player player;

    public SushiMakerContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.NULL);
    }

    public SushiMakerContainer(int id, @Nonnull Inventory playerInventory, ContainerLevelAccess worldPosCallable) {
        super(UtilcraftContainer.SUSHI_MAKER_CONTAINER.get(), id);
        this.worldPosCallable = worldPosCallable;
        this.player = playerInventory.player;
        this.addSlot(new ResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 44));

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

    protected static void updateCraftingResult(AbstractContainerMenu container, @Nonnull Level world, Player player, CraftingContainer inventory, ResultContainer inventoryResult) {
        if (!world.isClientSide) {
            ServerPlayer serverplayerentity = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<SushiMakerRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(UtilcraftRecipeTypes.SUSHI_MAKER_RECIPE.get(), inventory, world);
            if (optional.isPresent()) {
                SushiMakerRecipe recipe = optional.get();
                if (inventoryResult.setRecipeUsed(world, serverplayerentity, recipe)) {
                    itemstack = recipe.assemble(inventory);
                }
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
        return stillValid(this.worldPosCallable, player, UtilcraftBlocks.SUSHI_MAKER.get());
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                this.worldPosCallable.execute((p_217067_2_, p_217067_3_) -> itemstack1.getItem().onCraftedBy(itemstack1, p_217067_2_, player));
                if (!this.moveItemStackTo(itemstack1, getSize(), 53, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= getSize() && index < 53) {
                if (!this.moveItemStackTo(itemstack1, 1, getSize(), false)) {
                    if (index < 44) {
                        if (!this.moveItemStackTo(itemstack1, 44, 53, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, getSize(), 44, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, getSize(), 53, false)) {
                return ItemStack.EMPTY;
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
            if (index == 0) {
                player.drop(itemstack1, false);
            }
        }

        return itemstack;
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
        return 17;
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
