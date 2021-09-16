package net.petersil98.utilcraft.items;

import net.minecraft.item.SignItem;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class SakuraSignItem extends SignItem {

    public SakuraSignItem(Properties properties) {
        super(properties,
                UtilcraftBlocks.SAKURA_SIGN.get(),
                UtilcraftBlocks.SAKURA_WALL_SIGN.get()
        );
    }
}
