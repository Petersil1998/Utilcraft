package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ShearsItem;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class SakuraSignItem extends ShearsItem {

    public SakuraSignItem() {
        super(new HoeItem.Properties()
                .stacksTo(16)
                .tab(Utilcraft.ITEM_GROUP),
                UtilcraftBlocks.SAKURA_SIGN,
                UtilcraftBlocks.SAKURA_WALL_SIGN
        );
    }
}
