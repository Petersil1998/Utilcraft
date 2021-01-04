package net.petersil98.utilcraft.food;

import net.minecraft.item.*;

import net.petersil98.utilcraft.Utilcraft;

public class AppleJuice extends Item {

    public AppleJuice(){
        super(new Properties()
            .food(new Food.Builder()
                .hunger(8)
                .fastToEat()
                .saturation(3)
                //.effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 1F)
                .build()
            )
            .group(Utilcraft.ITEM_GROUP)
        );
    }
}
