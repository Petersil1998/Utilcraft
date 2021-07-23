package net.petersil98.utilcraft.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

public class Baguette extends Item {

    public Baguette(){
        super(new Properties()
                .food(new Food.Builder()
                        .nutrition(6)
                        .build()
                )
                .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
