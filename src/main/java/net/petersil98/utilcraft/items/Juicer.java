package net.petersil98.utilcraft.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.world.item.Item.Properties;

public class Juicer extends Item {

    public Juicer() {
        super(new Properties()
                .tab(Utilcraft.ITEM_GROUP)
                .stacksTo(1)
                .craftRemainder(UtilcraftItems.JUICER)
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
