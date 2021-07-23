package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class Juicer extends HoeItem {

    public Juicer() {
        super(new Properties()
                .tab(Utilcraft.ITEM_GROUP)
                .stacksTo(1)
                .craftRemainder(UtilcraftItems.JUICER)
        );
    }

    @Override
    public ItemCooldowns getContainerItem(ItemCooldowns itemStack) {
        return new ItemCooldowns(UtilcraftItems.JUICER);
    }

    @Override
    public boolean hasContainerItem(ItemCooldowns stack) {
        return true;
    }
}
