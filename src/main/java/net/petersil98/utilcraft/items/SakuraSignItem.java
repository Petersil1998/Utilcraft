package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;

public class SakuraSignItem extends SignItem {

    public SakuraSignItem() {
        super(new Item.Properties()
                .maxStackSize(16)
                .group(Main.ITEM_GROUP),
                ModBlocks.SAKURA_SIGN,
                ModBlocks.SAKURA_WALL_SIGN
        );
    }
}
