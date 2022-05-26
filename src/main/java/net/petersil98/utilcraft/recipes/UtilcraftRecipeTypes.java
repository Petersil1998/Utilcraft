package net.petersil98.utilcraft.recipes;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftRecipeTypes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, Utilcraft.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, Utilcraft.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> SUSHI_MAKER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("sushi_maker", SushiMakerRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<SushiMakerRecipe>> SUSHI_MAKER_RECIPE = RECIPE_TYPES.register("sushi_maker", () -> new RecipeType<>() {});
}
