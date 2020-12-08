package net.petersil98.utilcraft.data.capabilities.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class CapabilityInventory {
    @CapabilityInject(IInventory.class)
    public static Capability<IInventory> INVENTORY_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IInventory.class, new Storage(), DefaultInventory::new);
    }

    public static class Storage implements Capability.IStorage<IInventory> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IInventory> capability, IInventory instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            IItemHandler inventory = instance.getInventory();
            if(inventory != null) {
                ListNBT list = new ListNBT();
                for (int i = 0; i < inventory.getSlots(); i++) {
                    CompoundNBT nbt = new CompoundNBT();
                    ItemStack stack = inventory.getStackInSlot(i);
                    stack.write(nbt);
                    nbt.putInt("slot", i);
                    list.add(nbt);
                }
                tag.put("items", list);
                tag.putInt("size", inventory.getSlots());
            }
            return tag;
        }

        @Override
        public void readNBT(Capability<IInventory> capability, IInventory instance, Direction side, INBT nbt) {
            CompoundNBT tag = ((CompoundNBT)nbt);
            ListNBT items = (ListNBT) tag.get("items");
            ItemStackHandler inventory;
            if(items != null) {
                inventory = new ItemStackHandler(tag.getInt("size"));
                for (INBT inbt : items) {
                    CompoundNBT item = (CompoundNBT) inbt;
                    inventory.setStackInSlot(item.getInt("slot"), ItemStack.read(item));
                }
            } else {
                inventory = new ItemStackHandler(27);
            }
            instance.setInventory(inventory);
        }
    }
}
