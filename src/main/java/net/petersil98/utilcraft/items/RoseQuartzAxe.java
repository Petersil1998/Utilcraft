package net.petersil98.utilcraft.items;

import net.minecraft.item.*;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.items.custom.ModItemTier;

public class RoseQuartzAxe extends AxeItem {
    public RoseQuartzAxe() {
        super(ModItemTier.ROSE_QUARTZ, 5.0F, -3.0F, new Item.Properties().group(Main.itemGroup));
    }
}
