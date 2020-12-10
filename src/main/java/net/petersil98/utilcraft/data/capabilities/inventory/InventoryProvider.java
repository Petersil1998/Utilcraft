package net.petersil98.utilcraft.data.capabilities.inventory;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryProvider implements ICapabilitySerializable<INBT> {

    private ItemStackHandler inventory;
    private final LazyOptional<IItemHandler> inventoryOptional = LazyOptional.of(() -> inventory);

    public InventoryProvider(int size) {
        inventory = new ItemStackHandler(size);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryOptional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public INBT serializeNBT() {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == null) {
            return new ListNBT();
        } else {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inventory, null);
        }
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY != null) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inventory, null, nbt);
        }
    }
}