package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.petersil98.utilcraft.Utilcraft;

public class TNTFinder extends HoeItem {

    public TNTFinder(){
        super(new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1));
    }

    public static int getRadius(){
        return 5;
    }
}
