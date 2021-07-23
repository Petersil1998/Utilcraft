package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzShovel extends ShovelItem {
    public RoseQuartzShovel() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 2F, -2.8F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
