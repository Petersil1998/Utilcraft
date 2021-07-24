package net.petersil98.utilcraft.data.capabilities.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryProvider implements ICapabilitySerializable<CompoundTag> {

    private ItemStackHandler inventory;
    private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> inventory);

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
    public CompoundTag serializeNBT() {
        ItemStackHandler instance = inventoryOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        return instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ItemStackHandler instance = inventoryOptional.orElseThrow(() -> new IllegalArgumentException("Lazy optional is uninitialized"));
        instance.deserializeNBT(nbt);
    }
}