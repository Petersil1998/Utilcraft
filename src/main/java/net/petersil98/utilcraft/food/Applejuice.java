package net.petersil98.utilcraft.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
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

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
        if (playerentity == null || !playerentity.abilities.isCreativeMode) {
            entityLiving.onFoodEaten(worldIn, stack);
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        return stack;
    }
}
