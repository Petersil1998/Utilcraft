package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzSuperShovel extends ShovelItem {

    public RoseQuartzSuperShovel() {
        super(UtilcraftItemTier.SUPER_ROSE_QUARTZ, 2F, -2.8F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
