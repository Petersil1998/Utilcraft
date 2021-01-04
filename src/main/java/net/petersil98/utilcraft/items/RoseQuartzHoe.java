package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzHoe extends HoeItem {
    public RoseQuartzHoe() {
        super(ModItemTier.ROSE_QUARTZ, -5, 0.0F, new Item.Properties().group(Main.ITEM_GROUP));
    }
}
