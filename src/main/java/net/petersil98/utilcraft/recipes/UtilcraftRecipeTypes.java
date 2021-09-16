package net.petersil98.utilcraft.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftRecipeTypes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Utilcraft.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> SUSHI_MAKER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("sushi_maker", SushiMakerRecipe.Serializer::new);
    public static IRecipeType<SushiMakerRecipe> SUSHI_MAKER_RECIPE = IRecipeType.register("sushi_maker");
}
