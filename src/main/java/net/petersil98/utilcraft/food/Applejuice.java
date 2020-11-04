package net.petersil98.utilcraft.food;

import net.minecraft.item.*;

import net.petersil98.utilcraft.Main;

public class Applejuice extends Item {

    public Applejuice(){
        super(new Properties()
            .food(new Food.Builder()
                .hunger(8)
                .fastToEat()
                .saturation(3)
                //.effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 1F)
                .build()
            )
            .group(Main.setup.itemGroup)
        );
    }
}
