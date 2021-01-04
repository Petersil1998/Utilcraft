package net.petersil98.utilcraft.items;

import net.minecraft.item.Item;
import net.petersil98.utilcraft.Main;

public class TNTFinder extends Item {

    public TNTFinder(){
        super(new Item.Properties().group(Main.ITEM_GROUP));
    }

    public static int getRadius(){
        return 5;
    }
}
