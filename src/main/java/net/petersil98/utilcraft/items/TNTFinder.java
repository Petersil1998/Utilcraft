package net.petersil98.utilcraft.items;

import net.minecraft.world.item.Item;
import net.petersil98.utilcraft.Utilcraft;

public class TNTFinder extends Item {

    public TNTFinder(){
        super(new Item.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1));
    }

    public static int getRadius(){
        return 5;
    }
}
