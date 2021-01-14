package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.petersil98.utilcraft.Utilcraft;

public class Juicer extends Item {

    public Juicer() {
        super(new Properties()
                .group(Utilcraft.ITEM_GROUP)
                .maxStackSize(1)
                .containerItem(UtilcraftItems.JUICER)
        );
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(UtilcraftItems.JUICER);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
