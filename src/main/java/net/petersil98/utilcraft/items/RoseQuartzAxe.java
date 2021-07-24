package net.petersil98.utilcraft.items;

import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class RoseQuartzAxe extends AxeItem {
    public RoseQuartzAxe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 5.0F, -3.0F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
