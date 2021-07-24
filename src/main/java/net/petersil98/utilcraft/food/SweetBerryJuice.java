package net.petersil98.utilcraft.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.world.item.Item.Properties;

public class SweetBerryJuice extends Item {

    public SweetBerryJuice(){
        super(new Properties()
            .food(new FoodProperties.Builder()
                .nutrition(4)
                .fast()
                .saturationMod(0.8f)
                .build()
            )
            .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
