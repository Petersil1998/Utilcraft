package net.petersil98.utilcraft.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.Main;

public class Baguette extends Item {

    public Baguette(){
        super(new Properties()
                .food(new Food.Builder()
                        .hunger(6)
                        .build()
                ).group(Main.setup.itemGroup)
        );
    }
}
