package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.blocks.sakura.SakuraSign;
import net.petersil98.utilcraft.blocks.sakura.SakuraWallSign;

public class SakuraSignItem extends SignItem {

    public SakuraSignItem() {
        super(new Item.Properties()
                .maxStackSize(16)
                .group(Main.setup.itemGroup),
                ModBlocks.SAKURASIGN,
                ModBlocks.SAKURAWALLSIGN
        );
    }
}
