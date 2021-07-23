package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ServerItemCooldowns;

public class RoseQuartzShovel extends ServerItemCooldowns {
    public RoseQuartzShovel() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 2F, -2.8F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
