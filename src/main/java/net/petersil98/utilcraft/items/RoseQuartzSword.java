package net.petersil98.utilcraft.items;

import net.minecraft.item.SwordItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzSword extends SwordItem {
    public RoseQuartzSword() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 5, -2.0F, new Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
