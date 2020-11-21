package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzSuperShovel extends ShovelItem {

    public RoseQuartzSuperShovel() {
        super(ModItemTier.SUPER_ROSE_QUARTZ, 2F, -2.8F, (new Item.Properties()).group(Main.itemGroup));
    }
}
