package net.petersil98.utilcraft.items;

import net.minecraft.world.item.SwordItem;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTiers;

public class RoseQuartzSword extends SwordItem {

    public RoseQuartzSword(Properties properties) {
        super(UtilcraftItemTiers.ROSE_QUARTZ, 5, -2.0F, properties);
    }
}
