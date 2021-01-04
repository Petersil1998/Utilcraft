package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzShovel extends ShovelItem {
    public RoseQuartzShovel() {
        super(ModItemTier.ROSE_QUARTZ, 2F, -2.8F, new Item.Properties().group(Main.ITEM_GROUP));
    }
}
