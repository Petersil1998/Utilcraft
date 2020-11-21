package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzPickaxe extends PickaxeItem {
    public RoseQuartzPickaxe() {
        super(ModItemTier.ROSE_QUARTZ, 1, -2.5F, new Item.Properties().group(Main.itemGroup));
    }
}
