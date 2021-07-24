package net.petersil98.utilcraft.items;

import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;

public class RoseQuartzHoe extends HoeItem {
    public RoseQuartzHoe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, -5, 0.0F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
