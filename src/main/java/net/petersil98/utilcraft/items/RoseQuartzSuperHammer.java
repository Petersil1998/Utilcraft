package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzSuperHammer extends PickaxeItem {

    public RoseQuartzSuperHammer() {
        super(ModItemTier.SUPER_ROSE_QUARTZ, 1, -2.5F, new Item.Properties().group(Main.setup.itemGroup));
    }
}
