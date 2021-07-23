package net.petersil98.utilcraft.food;

import net.minecraft.item.*;

import net.petersil98.utilcraft.Utilcraft;

import net.minecraft.item.Item.Properties;

import net.minecraft.world.entity.vehicle.package-info;
import net.minecraft.world.item.HoeItem;

public class AppleJuice extends HoeItem {

    public AppleJuice(){
        super(new Properties()
            .food(new package-info.Builder()
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
