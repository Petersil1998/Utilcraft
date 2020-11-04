package net.petersil98.utilcraft.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.Main;

public class SweetBerryjuice extends Item {

    public SweetBerryjuice(){
        super(new Properties()
            .food(new Food.Builder()
                .hunger(4)
                .fastToEat()
                .saturation(0.8f)
                .build()
            )
            .group(Main.setup.itemGroup)
        );
    }
}
