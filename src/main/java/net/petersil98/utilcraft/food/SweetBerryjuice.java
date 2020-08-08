package net.petersil98.utilcraft.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
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
