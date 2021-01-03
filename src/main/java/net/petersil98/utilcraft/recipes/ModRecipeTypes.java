package net.petersil98.utilcraft.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Main;

@ObjectHolder(Main.MOD_ID)
public class ModRecipeTypes {
    @ObjectHolder("sushi_maker")
    public static IRecipeSerializer<?> SUSHI_MAKER_RECIPE_SERIALIZER;
    public static IRecipeType<SushiMakerRecipe> SUSHI_MAKER_RECIPE = IRecipeType.register("sushi_maker");
}
