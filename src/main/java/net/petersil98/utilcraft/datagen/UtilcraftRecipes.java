package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.food.UtilcraftFoods;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.recipes.SushiMakerRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class UtilcraftRecipes extends RecipeProvider {

    public UtilcraftRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        registerShapedRecipes(consumer);
        registerShapelessRecipes(consumer);
        registerSmeltingRecipes(consumer);
        //registerSushiMakerRecipes(consumer);
    }

    private void registerShapedRecipes(Consumer<IFinishedRecipe> consumer) {
        registerButchersKnife(consumer);
        registerCompressedCobblestone(consumer);
        registerDisenchantmentTable(consumer);
        registerFlour(consumer);
        registerGoldBrick(consumer);
        registerGoldSlab(consumer);
        registerGoldStairs(consumer);
        registerGoldWall(consumer);
        registerJuicer(consumer);
        registerRedstoneSlab(consumer);
        registerRedstoneStairs(consumer);
        registerRoseQuartzAxe(consumer);
        registerRoseQuartzBlock(consumer);
        registerRoseQuartzBoots(consumer);
        registerRoseQuartzChestplate(consumer);
        registerRoseQuartzHelmet(consumer);
        registerRoseQuartzHoe(consumer);
        registerRoseQuartzLeggings(consumer);
        registerRoseQuartzPickaxe(consumer);
        registerRoseQuartzShovel(consumer);
        registerRoseQuartzSuperHammer(consumer);
        registerRoseQuartzSuperShovel(consumer);
        registerRoseQuartzSword(consumer);
        registerSakuraDoor(consumer);
        registerSakuraFence(consumer);
        registerSakuraFenceGate(consumer);
        registerSakuraPressurePlate(consumer);
        registerSakuraSign(consumer);
        registerSakuraSlab(consumer);
        registerSakuraStairs(consumer);
        registerSakuraTrapdoor(consumer);
        registerSecureChest(consumer);
        registerSideAcaciaSlab(consumer);
        registerSideBirchSlab(consumer);
        registerSideCobblestoneSlab(consumer);
        registerSideDarkOakSlab(consumer);
        registerSideGoldSlab(consumer);
        registerSideJungleSlab(consumer);
        registerSideOakSlab(consumer);
        registerSideRedstoneSlab(consumer);
        registerSideSakuraSlab(consumer);
        registerSideSpruceSlab(consumer);
        registerSideStoneSlab(consumer);
        registerTNTFinder(consumer);
        registerTravelersBackpack(consumer);
        registerGlassStairs(consumer);
    }

    private void registerShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        registerAppleJuice(consumer);
        registerCobblestone(consumer);
        registerRoseQuartz(consumer);
        registerSakuraButton(consumer);
        registerSakuraPlanks(consumer);
        registerSweetBerryJuice(consumer);
        registerLeather(consumer);
    }

    private void registerSmeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        registerBaguette(consumer);
        registerSilverIngot(consumer);
    }

    private void registerSushiMakerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerTest(consumer);
    }

    private void registerButchersKnife(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.BUTCHERS_KNIFE)
                .pattern("*")
                .pattern("#")
                .pattern("-")
                .define('*', UtilcraftItems.SILVER_INGOT)
                .define('#', Items.DRIED_KELP)
                .define('-', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("silver_ingot", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.SILVER_INGOT))
                .unlockedBy("dried_kelp", InventoryChangeTrigger.Instance.hasItems(Items.DRIED_KELP))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerCompressedCobblestone(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', Blocks.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .save(consumer);
    }

    private void registerDisenchantmentTable(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.DISENCHANTMENT_TABLE)
                .pattern(" # ")
                .pattern("+*+")
                .pattern("***")
                .define('#', Items.BOOK)
                .define('+', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Blocks.OBSIDIAN)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("book", InventoryChangeTrigger.Instance.hasItems(Items.BOOK))
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("obsidian", InventoryChangeTrigger.Instance.hasItems(Blocks.OBSIDIAN))
                .save(consumer);
    }

    private void registerFlour(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.FLOUR)
                .pattern("##")
                .define('#', Items.WHEAT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("wheat", InventoryChangeTrigger.Instance.hasItems(Items.WHEAT))
                .save(consumer);
    }

    private void registerGoldBrick(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_BRICK, 4)
                .pattern("##")
                .pattern("##")
                .define('#', Items.GOLD_INGOT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_ingot", InventoryChangeTrigger.Instance.hasItems(Items.GOLD_INGOT))
                .save(consumer);
    }

    private void registerGoldSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_SLAB, 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerGoldStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerGoldWall(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_WALL, 6)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerJuicer(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.JUICER)
                .pattern(" # ")
                .pattern("---")
                .define('#', Items.IRON_INGOT)
                .define('-', Blocks.SMOOTH_STONE_SLAB)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT))
                .unlockedBy("smooth_stone_slab", InventoryChangeTrigger.Instance.hasItems(Blocks.SMOOTH_STONE_SLAB))
                .save(consumer);
    }

    private void registerRedstoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_SLAB)
                .pattern("###")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.Instance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRedstoneStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.Instance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRoseQuartzAxe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_AXE)
                .pattern("##")
                .pattern("#*")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzBlock(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzBoots(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzChestplate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzHelmet(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzHoe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HOE)
                .pattern("##")
                .pattern(" *")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzLeggings(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzPickaxe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .pattern("###")
                .pattern(" * ")
                .pattern(" * ")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzShovel(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .pattern("#")
                .pattern("*")
                .pattern("*")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzSuperHammer(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_pickaxe", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ_PICKAXE))
                .save(consumer);
    }

    private void registerRoseQuartzSuperShovel(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_shovel", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ_SHOVEL))
                .save(consumer);
    }

    private void registerRoseQuartzSword(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SWORD)
                .pattern(" # ")
                .pattern(" # ")
                .pattern("-*-")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', Items.STICK)
                .define('-', Blocks.ROSE_BUSH)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .unlockedBy("rose_bush", InventoryChangeTrigger.Instance.hasItems(Blocks.ROSE_BUSH))
                .save(consumer);
    }

    private void registerSakuraDoor(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_DOOR)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_door")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraFence(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE)
                .pattern("W#W")
                .pattern("W#W")
                .define('#', Items.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_fence")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("sticks", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraFenceGate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE_GATE)
                .pattern("#W#")
                .pattern("#W#")
                .define('#', Items.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_fence_gate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("sticks", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraPressurePlate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_PRESSURE_PLATE)
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_pressure_plate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraSign(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SIGN, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .define('X', Items.STICK)
                .group("sign")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("stick", InventoryChangeTrigger.Instance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SLAB, 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_slab")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_stairs")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraTrapdoor(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_TRAPDOOR, 2)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_trapdoor")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSecureChest(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SECURE_CHEST)
                .pattern("###")
                .pattern("#*#")
                .pattern("###")
                .define('#', Ingredient.of(ItemTags.PLANKS))
                .define('*', UtilcraftItems.SILVER_INGOT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("planks", has(ItemTags.PLANKS))
                .unlockedBy("silver_ingot", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.SILVER_INGOT))
                .save(consumer);
    }

    private void registerSideAcaciaSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.ACACIA_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("acacia_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.ACACIA_PLANKS))
                .save(consumer);
    }

    private void registerSideBirchSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.BIRCH_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("birch_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.BIRCH_PLANKS))
                .save(consumer);
    }

    private void registerSideCobblestoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .save(consumer);
    }

    private void registerSideDarkOakSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.DARK_OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("dark_oak_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.DARK_OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideGoldSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_GOLD_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerSideJungleSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.JUNGLE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("jungle_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.JUNGLE_PLANKS))
                .save(consumer);
    }

    private void registerSideOakSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_OAK_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("oak_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideRedstoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.Instance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerSideSakuraSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSideSpruceSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.SPRUCE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("spruce_planks", InventoryChangeTrigger.Instance.hasItems(Blocks.SPRUCE_PLANKS))
                .save(consumer);
    }

    private void registerSideStoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_STONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.STONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.Instance.hasItems(Blocks.STONE))
                .save(consumer);
    }

    private void registerTNTFinder(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TNT_FINDER)
                .pattern(" + ")
                .pattern("+#+")
                .pattern(" + ")
                .define('#', Items.COMPASS)
                .define('+', Blocks.TNT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.Instance.hasItems(Items.COMPASS))
                .unlockedBy("tnt", InventoryChangeTrigger.Instance.hasItems(Blocks.TNT))
                .save(consumer);
    }

    private void registerTravelersBackpack(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TRAVELERS_BACKPACK)
                .pattern("*X*")
                .pattern("-#-")
                .pattern("000")
                .define('#', Blocks.BARREL)
                .define('-', Items.STRING)
                .define('0', Blocks.OBSIDIAN)
                .define('*', Items.LEATHER)
                .define('X', Items.DIAMOND)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("barrel", InventoryChangeTrigger.Instance.hasItems(Blocks.BARREL))
                .unlockedBy("string", InventoryChangeTrigger.Instance.hasItems(Items.STRING))
                .unlockedBy("obsidian", InventoryChangeTrigger.Instance.hasItems(Blocks.OBSIDIAN))
                .unlockedBy("leather", InventoryChangeTrigger.Instance.hasItems(Items.LEATHER))
                .unlockedBy("diamond", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND))
                .save(consumer);
    }

    private void registerGlassStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GLASS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Blocks.GLASS)
                .unlockedBy("glass", InventoryChangeTrigger.Instance.hasItems(Blocks.GLASS))
                .save(consumer);
    }

    private void registerAppleJuice(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftFoods.APPLE_JUICE)
                .requires(Items.APPLE)
                .requires(UtilcraftItems.JUICER)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.JUICER))
                .unlockedBy("apple", InventoryChangeTrigger.Instance.hasItems(Items.APPLE))
                .save(consumer);
    }

    private void registerCobblestone(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Blocks.COBBLESTONE, 9)
                .requires(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("compressed_cobblestone", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.COMPRESSED_COBBLESTONE))
                .save(consumer);
    }

    private void registerRoseQuartz(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftItems.ROSE_QUARTZ, 9)
                .requires(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_block", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.ROSE_QUARTZ_BLOCK))
                .save(consumer);
    }

    private void registerSakuraButton(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_BUTTON)
                .requires(UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_button")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraPlanks(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_PLANKS, 4)
                .requires(UtilcraftBlocks.SAKURA_LOG)
                .group("planks")
                .unlockedBy("sakura_log", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SAKURA_LOG))
                .save(consumer);
    }

    private void registerSweetBerryJuice(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftFoods.SWEET_BERRY_JUICE)
                .requires(Items.SWEET_BERRIES)
                .requires(UtilcraftItems.JUICER)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.JUICER))
                .unlockedBy("sweet_berries", InventoryChangeTrigger.Instance.hasItems(Items.SWEET_BERRIES))
                .save(consumer);
    }

    private void registerLeather(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Items.LEATHER, 5)
                .requires(Items.SADDLE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("saddle", InventoryChangeTrigger.Instance.hasItems(Items.SADDLE))
                .save(consumer);
    }

    private void registerBaguette(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(UtilcraftItems.FLOUR), UtilcraftFoods.BAGUETTE, 0.15f, 200)
                .unlockedBy("flour", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.FLOUR))
                .save(consumer);
    }

    private void registerSilverIngot(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 200)
                .unlockedBy("silver_ore", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SILVER_ORE))
                .save(consumer);

        CookingRecipeBuilder.blasting(Ingredient.of(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 100)
                .unlockedBy("silver_ore", InventoryChangeTrigger.Instance.hasItems(UtilcraftBlocks.SILVER_ORE))
                .save(consumer, new ResourceLocation(Utilcraft.MOD_ID, UtilcraftItems.SILVER_INGOT.getRegistryName().getPath()+"_from_blasting"));
    }

    private void registerTest(Consumer<IFinishedRecipe> consumer) {
        SushiMakerRecipeBuilder.sushiMakerRecipe(UtilcraftBlocks.SUSHI_MAKER)
                .patternLine("****")
                .patternLine("#  #")
                .patternLine("#  #")
                .patternLine("****")
                .key('#', Blocks.COBBLESTONE)
                .key('*', Blocks.OBSIDIAN)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.hasItems(Blocks.OBSIDIAN))
                .build(consumer);
    }
}