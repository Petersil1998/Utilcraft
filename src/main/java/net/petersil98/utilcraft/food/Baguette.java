package net.petersil98.utilcraft.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.world.item.Item.Properties;

public class Baguette extends Item {

    public Baguette(){
        super(new Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(6)
                        .build()
                )
                .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
