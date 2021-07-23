package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzSuperHammer extends PickaxeItem {

    public RoseQuartzSuperHammer() {
        super(UtilcraftItemTier.SUPER_ROSE_QUARTZ, 1, -2.5F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
