package net.petersil98.utilcraft.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Utilcraft.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> SUSHI_MAKER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("sushi_maker", SushiMakerRecipe.Serializer::new);
    public static RecipeType<SushiMakerRecipe> SUSHI_MAKER_RECIPE = RecipeType.register("sushi_maker");
}
