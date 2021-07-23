package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.MilkBucketItem;

public class RoseQuartzPickaxe extends MilkBucketItem {
    public RoseQuartzPickaxe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 1, -2.5F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
