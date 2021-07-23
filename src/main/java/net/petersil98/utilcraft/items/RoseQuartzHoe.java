package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.HoeItem;

public class RoseQuartzHoe extends FoodOnAStickItem {
    public RoseQuartzHoe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, -5, 0.0F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
