package net.petersil98.utilcraft.items;

import net.minecraft.world.item.HoeItem;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class Flour extends HoeItem {

    public Flour() {
        super(new Properties()
                .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
