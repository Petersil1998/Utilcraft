package net.petersil98.utilcraft.food;


import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class AppleJuice extends Item {

    public AppleJuice(){
        super(new Properties()
            .food(new FoodProperties.Builder()
                .nutrition(8)
                .fast()
                .saturationMod(3)
                //.effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 1F)
                .build()
            )
            .tab(Utilcraft.ITEM_GROUP)
        );
    }
}
