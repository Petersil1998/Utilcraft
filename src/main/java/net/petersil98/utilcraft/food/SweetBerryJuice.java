package net.petersil98.utilcraft.food;

import net.minecraft.world.entity.vehicle.package-info;
import net.minecraft.world.item.HoeItem;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class SweetBerryJuice extends HoeItem {

    public SweetBerryJuice(){
        super(new Properties()
            .food(new package-info.Builder()
                .nutrition(4)
                .fast()
                .saturationMod(0.8f)
                .build()
            )
            .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
