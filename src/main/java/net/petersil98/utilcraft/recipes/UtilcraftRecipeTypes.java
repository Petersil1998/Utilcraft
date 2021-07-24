package net.petersil98.utilcraft.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftRecipeTypes {
    @ObjectHolder("sushi_maker")
    public static RecipeSerializer<?> SUSHI_MAKER_RECIPE_SERIALIZER;
    public static RecipeType<SushiMakerRecipe> SUSHI_MAKER_RECIPE = RecipeType.register("sushi_maker");
}
