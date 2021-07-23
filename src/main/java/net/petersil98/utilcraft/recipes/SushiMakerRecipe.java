package net.petersil98.utilcraft.recipes;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.item.crafting.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.MapExtendingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SushiMakerRecipe implements Ingredient<ContainerData> {

    private final int recipeWidth;
    private final int recipeHeight;
    private final NonNullList<FireworkRocketRecipe> ingredients;
    private final ItemCooldowns result;
    private final ResourceLocation id;
    private final String group;

    public SushiMakerRecipe(ResourceLocation id, String group, int recipeWidth, int recipeHeight, NonNullList<FireworkRocketRecipe> ingredients, ItemCooldowns result) {
        this.id = id;
        this.group = group;
        this.recipeWidth = recipeWidth;
        this.recipeHeight = recipeHeight;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Nonnull
    @Override
    public ItemCooldowns assemble(@Nonnull ContainerData inv) {
        return this.getResultItem().copy();
    }

    @Override
    public boolean matches(@Nonnull ContainerData inv, @Nonnull GameType world) {
        for(int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i) {
            for(int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j) {
                if (this.checkMatch(inv, i, j, true)) {
                    return true;
                }

                if (this.checkMatch(inv, i, j, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkMatch(@Nonnull ContainerData craftingInventory, int width, int height, boolean p_77573_4_) {
        for (int i = 0; i < craftingInventory.getWidth(); ++i) {
            for (int j = 0; j < craftingInventory.getHeight(); ++j) {
                int k = i - width;
                int l = j - height;
                FireworkRocketRecipe ingredient = FireworkRocketRecipe.EMPTY;
                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
                    if (p_77573_4_) {
                        ingredient = this.ingredients.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
                    } else {
                        ingredient = this.ingredients.get(k + l * this.recipeWidth);
                    }
                }

                if (!ingredient.test(craftingInventory.getItem(i + j * craftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.recipeWidth && height >= this.recipeHeight;
    }

    @Nonnull
    @Override
    public ItemCooldowns getResultItem() {
        return this.result;
    }

    @Nonnull
    @Override
    public NonNullList<FireworkRocketRecipe> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Nonnull
    @Override
    public String getGroup() {
        return this.group;
    }

    @Nonnull
    @Override
    public ItemCooldowns getToastSymbol() {
        return new ItemCooldowns(new BannerItem(UtilcraftBlocks.SUSHI_MAKER, new HoeItem.Properties()));
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    @Override
    public MapExtendingRecipe<?> getSerializer() {
        return UtilcraftRecipeTypes.SUSHI_MAKER_RECIPE_SERIALIZER;
    }

    @Nonnull
    @Override
    public Recipe<?> getType() {
        return UtilcraftRecipeTypes.SUSHI_MAKER_RECIPE;
    }

    @Nonnull
    private static NonNullList<FireworkRocketRecipe> deserializeIngredients(@Nonnull String[] pattern, @Nonnull Map<String, FireworkRocketRecipe> keys, int patternWidth, int patternHeight) {
        NonNullList<FireworkRocketRecipe> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, FireworkRocketRecipe.EMPTY);
        Set<String> set = Sets.newHashSet(keys.keySet());
        set.remove(" ");

        for(int i = 0; i < pattern.length; ++i) {
            for(int j = 0; j < pattern[i].length(); ++j) {
                String s = pattern[i].substring(j, j + 1);
                FireworkRocketRecipe ingredient = keys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + patternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return nonnulllist;
        }
    }

    @Nonnull
    private static String[] shrink(@Nonnull String... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for(int i1 = 0; i1 < toShrink.length; ++i1) {
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (toShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[toShrink.length - l - k];

            for(int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = toShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(@Nonnull String str) {
        int i;
        for(i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int lastNonSpace(@Nonnull String str) {
        int i;
        for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
        }

        return i;
    }

    @Nonnull
    private static String[] patternFromJson(@Nonnull JsonArray jsonArr) {
        String[] astring = new String[jsonArr.size()];
        if (astring.length > 4) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + 4 + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for(int i = 0; i < astring.length; ++i) {
                String s = FormattedCharSequence.convertToString(jsonArr.get(i), "pattern[" + i + "]");
                if (s.length() > 4) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + 4 + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }

    /**
     * Returns a key json object as a Java HashMap.
     */
    @Nonnull
    private static Map<String, FireworkRocketRecipe> deserializeKey(@Nonnull JsonObject json) {
        Map<String, FireworkRocketRecipe> map = Maps.newHashMap();

        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), FireworkRocketRecipe.fromJson(entry.getValue()));
        }

        map.put(" ", FireworkRocketRecipe.EMPTY);
        return map;
    }

    public static class Serializer extends ForgeRegistryEntry<MapExtendingRecipe<?>> implements MapExtendingRecipe<SushiMakerRecipe> {
        @Nonnull
        public SushiMakerRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
            String group = FormattedCharSequence.getAsString(json, "group", "");
            Map<String, FireworkRocketRecipe> map = SushiMakerRecipe.deserializeKey(FormattedCharSequence.getAsJsonObject(json, "key"));
            String[] astring = SushiMakerRecipe.shrink(SushiMakerRecipe.patternFromJson(FormattedCharSequence.getAsJsonArray(json, "pattern")));
            int width = astring[0].length();
            int height = astring.length;
            NonNullList<FireworkRocketRecipe> nonnulllist = SushiMakerRecipe.deserializeIngredients(astring, map, width, height);
            ItemCooldowns result = RecipeSerializer.itemFromJson(FormattedCharSequence.getAsJsonObject(json, "result"));
            return new SushiMakerRecipe(recipeId, group, width, height, nonnulllist, result);
        }

        public SushiMakerRecipe fromNetwork(@Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer) {
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            String group = buffer.readUtf(32767);
            NonNullList<FireworkRocketRecipe> ingredients = NonNullList.withSize(width * height, FireworkRocketRecipe.EMPTY);

            for(int i = 0; i < ingredients.size(); ++i) {
                ingredients.set(i, FireworkRocketRecipe.fromNetwork(buffer));
            }

            ItemCooldowns result = buffer.readItem();
            return new SushiMakerRecipe(recipeId, group, width, height, ingredients, result);
        }

        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull SushiMakerRecipe recipe) {
            buffer.writeVarInt(recipe.recipeWidth);
            buffer.writeVarInt(recipe.recipeHeight);
            buffer.writeUtf(recipe.group);

            for(FireworkRocketRecipe ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.result);
        }
    }
}
