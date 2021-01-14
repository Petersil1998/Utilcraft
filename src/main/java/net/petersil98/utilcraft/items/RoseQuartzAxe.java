package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzAxe extends AxeItem {
    public RoseQuartzAxe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 5.0F, -3.0F, new Item.Properties().group(Utilcraft.ITEM_GROUP));
    }
}
