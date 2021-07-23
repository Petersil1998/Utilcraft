package net.petersil98.utilcraft.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class SweetBerryJuice extends Item {

    public SweetBerryJuice(){
        super(new Properties()
            .food(new Food.Builder()
                .nutrition(4)
                .fast()
                .saturationMod(0.8f)
                .build()
            )
            .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
