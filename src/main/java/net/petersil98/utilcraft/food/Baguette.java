package net.petersil98.utilcraft.food;

import net.minecraft.world.entity.vehicle.package-info;
import net.minecraft.world.item.HoeItem;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class Baguette extends HoeItem {

    public Baguette(){
        super(new Properties()
                .food(new package-info.Builder()
                        .nutrition(6)
                        .build()
                )
                .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
