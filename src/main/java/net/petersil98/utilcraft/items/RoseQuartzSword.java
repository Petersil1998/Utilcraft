package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.SplashPotionItem;

public class RoseQuartzSword extends SplashPotionItem {
    public RoseQuartzSword() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 5, -2.0F, new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
