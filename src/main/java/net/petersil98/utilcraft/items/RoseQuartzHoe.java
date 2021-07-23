package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzHoe extends HoeItem {
    public RoseQuartzHoe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, -5, 0.0F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
