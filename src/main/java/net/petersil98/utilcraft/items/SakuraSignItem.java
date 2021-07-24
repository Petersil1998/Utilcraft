package net.petersil98.utilcraft.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class SakuraSignItem extends SignItem {

    public SakuraSignItem() {
        super(new Item.Properties()
                .stacksTo(16)
                .tab(Utilcraft.ITEM_GROUP),
                UtilcraftBlocks.SAKURA_SIGN,
                UtilcraftBlocks.SAKURA_WALL_SIGN
        );
    }
}
