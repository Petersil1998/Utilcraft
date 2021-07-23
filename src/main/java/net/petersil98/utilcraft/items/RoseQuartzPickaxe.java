package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.custom.UtilcraftItemTier;

public class RoseQuartzPickaxe extends PickaxeItem {
    public RoseQuartzPickaxe() {
        super(UtilcraftItemTier.ROSE_QUARTZ, 1, -2.5F, new Item.Properties().tab(Utilcraft.ITEM_GROUP));
    }
}
