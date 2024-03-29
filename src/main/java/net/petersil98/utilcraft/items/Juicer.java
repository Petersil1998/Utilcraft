package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Juicer extends Item {

    public Juicer(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(UtilcraftItems.JUICER.get());
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
