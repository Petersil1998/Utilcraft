package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.petersil98.utilcraft.Main;

public class SilverIngot extends Item {
    public SilverIngot() {
        super(new Item.Properties()
                .group(Main.ITEM_GROUP)
                .maxStackSize(1)
        );
    }
}
