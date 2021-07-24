package net.petersil98.utilcraft.items;

import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class RoseQuartzShovel extends ShovelItem {
    public RoseQuartzShovel() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 2F, -2.8F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
