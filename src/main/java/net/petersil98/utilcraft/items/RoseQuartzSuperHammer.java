package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.MilkBucketItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzSuperHammer extends MilkBucketItem {

    public RoseQuartzSuperHammer() {
        super(UtilcraftItemTier.SUPER_ROSE_QUARTZ, 1, -2.5F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
