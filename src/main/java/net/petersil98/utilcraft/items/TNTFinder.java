package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.petersil98.utilcraft.Utilcraft;

public class TNTFinder extends Item {

    public TNTFinder(){
        super(new Item.Properties().group(Utilcraft.ITEM_GROUP).maxStackSize(1));
    }

    public static int getRadius(){
        return 5;
    }
}
