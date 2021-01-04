package net.petersil98.utilcraft.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.Utilcraft;

public class SweetBerryJuice extends Item {

    public SweetBerryJuice(){
        super(new Properties()
            .food(new Food.Builder()
                .hunger(4)
                .fastToEat()
                .saturation(0.8f)
                .build()
            )
            .group(Utilcraft.ITEM_GROUP)
        );
    }
}
