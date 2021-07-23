package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ServerItemCooldowns;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzSuperShovel extends ServerItemCooldowns {

    public RoseQuartzSuperShovel() {
        super(UtilcraftItemTier.SUPER_ROSE_QUARTZ, 2F, -2.8F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
