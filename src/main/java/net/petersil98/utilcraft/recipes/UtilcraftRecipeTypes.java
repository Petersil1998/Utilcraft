package net.petersil98.utilcraft.recipes;

import net.minecraft.world.item.crafting.MapExtendingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.registries.ObjectHolder;
import net.petersil98.utilcraft.Utilcraft;

@ObjectHolder(Utilcraft.MOD_ID)
public class UtilcraftRecipeTypes {
    @ObjectHolder("sushi_maker")
    public static MapExtendingRecipe<?> SUSHI_MAKER_RECIPE_SERIALIZER;
    public static Recipe<SushiMakerRecipe> SUSHI_MAKER_RECIPE = Recipe.register("sushi_maker");
}
