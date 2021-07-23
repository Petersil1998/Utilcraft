package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.data.*;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.resources.ResourceLocation;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.food.UtilcraftFoods;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.recipes.SushiMakerRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;

public class UtilcraftRecipes extends RecipeProvider {

    public UtilcraftRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        registerShapedRecipes(consumer);
        registerShapelessRecipes(consumer);
        registerSmeltingRecipes(consumer);
        //registerSushiMakerRecipes(consumer);
    }

    private void registerShapedRecipes(Consumer<FinishedRecipe> consumer) {
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

    private void registerShapelessRecipes(Consumer<FinishedRecipe> consumer) {
        registerAppleJuice(consumer);
        registerCobblestone(consumer);
        registerRoseQuartz(consumer);
        registerSakuraButton(consumer);
        registerSakuraPlanks(consumer);
        registerSweetBerryJuice(consumer);
        registerLeather(consumer);
    }

    private void registerSmeltingRecipes(Consumer<FinishedRecipe> consumer) {
        registerBaguette(consumer);
        registerSilverIngot(consumer);
    }

    private void registerSushiMakerRecipes(Consumer<FinishedRecipe> consumer) {
        registerTest(consumer);
    }

    private void registerButchersKnife(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.BUTCHERS_KNIFE)
                .pattern("*")
                .pattern("#")
                .pattern("-")
                .define('*', UtilcraftItems.SILVER_INGOT)
                .define('#', ItemNameBlockItem.DRIED_KELP)
                .define('-', ItemNameBlockItem.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.SILVER_INGOT))
                .unlockedBy("dried_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.DRIED_KELP))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerCompressedCobblestone(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', BellBlock.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.COBBLESTONE))
                .save(consumer);
    }

    private void registerDisenchantmentTable(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.DISENCHANTMENT_TABLE)
                .pattern(" # ")
                .pattern("+*+")
                .pattern("***")
                .define('#', ItemNameBlockItem.BOOK)
                .define('+', UtilcraftItems.ROSE_QUARTZ)
                .define('*', BellBlock.OBSIDIAN)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("book", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.BOOK))
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.OBSIDIAN))
                .save(consumer);
    }

    private void registerFlour(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.FLOUR)
                .pattern("##")
                .define('#', ItemNameBlockItem.WHEAT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("wheat", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.WHEAT))
                .save(consumer);
    }

    private void registerGoldBrick(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_BRICK, 4)
                .pattern("##")
                .pattern("##")
                .define('#', ItemNameBlockItem.GOLD_INGOT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.GOLD_INGOT))
                .save(consumer);
    }

    private void registerGoldSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_SLAB, 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerGoldStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerGoldWall(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_WALL, 6)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerJuicer(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.JUICER)
                .pattern(" # ")
                .pattern("---")
                .define('#', ItemNameBlockItem.IRON_INGOT)
                .define('-', BellBlock.SMOOTH_STONE_SLAB)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.IRON_INGOT))
                .unlockedBy("smooth_stone_slab", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.SMOOTH_STONE_SLAB))
                .save(consumer);
    }

    private void registerRedstoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_SLAB)
                .pattern("###")
                .define('#', BellBlock.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRedstoneStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', BellBlock.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRoseQuartzAxe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_AXE)
                .pattern("##")
                .pattern("#*")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', ItemNameBlockItem.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzBlock(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzBoots(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzChestplate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzHelmet(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzHoe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HOE)
                .pattern("##")
                .pattern(" *")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', ItemNameBlockItem.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzLeggings(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .save(consumer);
    }

    private void registerRoseQuartzPickaxe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .pattern("###")
                .pattern(" * ")
                .pattern(" * ")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', ItemNameBlockItem.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzShovel(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .pattern("#")
                .pattern("*")
                .pattern("*")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', ItemNameBlockItem.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzSuperHammer(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ_PICKAXE))
                .save(consumer);
    }

    private void registerRoseQuartzSuperShovel(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ_SHOVEL))
                .save(consumer);
    }

    private void registerRoseQuartzSword(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SWORD)
                .pattern(" # ")
                .pattern(" # ")
                .pattern("-*-")
                .define('#', UtilcraftItems.ROSE_QUARTZ)
                .define('*', ItemNameBlockItem.STICK)
                .define('-', BellBlock.ROSE_BUSH)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .unlockedBy("rose_bush", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.ROSE_BUSH))
                .save(consumer);
    }

    private void registerSakuraDoor(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_DOOR)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_door")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraFence(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE)
                .pattern("W#W")
                .pattern("W#W")
                .define('#', ItemNameBlockItem.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_fence")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerSakuraFenceGate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE_GATE)
                .pattern("#W#")
                .pattern("#W#")
                .define('#', ItemNameBlockItem.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_fence_gate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerSakuraPressurePlate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_PRESSURE_PLATE)
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_pressure_plate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraSign(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SIGN, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .define('X', ItemNameBlockItem.STICK)
                .group("sign")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STICK))
                .save(consumer);
    }

    private void registerSakuraSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SLAB, 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_slab")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_stairs")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraTrapdoor(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_TRAPDOOR, 2)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_trapdoor")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSecureChest(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SECURE_CHEST)
                .pattern("###")
                .pattern("#*#")
                .pattern("###")
                .define('#', FireworkRocketRecipe.of(EntityTypeTags.PLANKS))
                .define('*', UtilcraftItems.SILVER_INGOT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("planks", has(EntityTypeTags.PLANKS))
                .unlockedBy("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.SILVER_INGOT))
                .save(consumer);
    }

    private void registerSideAcaciaSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.ACACIA_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("acacia_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.ACACIA_PLANKS))
                .save(consumer);
    }

    private void registerSideBirchSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.BIRCH_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("birch_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.BIRCH_PLANKS))
                .save(consumer);
    }

    private void registerSideCobblestoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.COBBLESTONE))
                .save(consumer);
    }

    private void registerSideDarkOakSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.DARK_OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("dark_oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.DARK_OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideGoldSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_GOLD_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.GOLD_BRICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK))
                .save(consumer);
    }

    private void registerSideJungleSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.JUNGLE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("jungle_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.JUNGLE_PLANKS))
                .save(consumer);
    }

    private void registerSideOakSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_OAK_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideRedstoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerSideSakuraSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSideSpruceSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.SPRUCE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("spruce_planks", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.SPRUCE_PLANKS))
                .save(consumer);
    }

    private void registerSideStoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftSideSlabs.SIDE_STONE_SLAB, 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', BellBlock.STONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.STONE))
                .save(consumer);
    }

    private void registerTNTFinder(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TNT_FINDER)
                .pattern(" + ")
                .pattern("+#+")
                .pattern(" + ")
                .define('#', ItemNameBlockItem.COMPASS)
                .define('+', BellBlock.TNT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.COMPASS))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.TNT))
                .save(consumer);
    }

    private void registerTravelersBackpack(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TRAVELERS_BACKPACK)
                .pattern("*X*")
                .pattern("-#-")
                .pattern("000")
                .define('#', BellBlock.BARREL)
                .define('-', ItemNameBlockItem.STRING)
                .define('0', BellBlock.OBSIDIAN)
                .define('*', ItemNameBlockItem.LEATHER)
                .define('X', ItemNameBlockItem.DIAMOND)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("barrel", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.BARREL))
                .unlockedBy("string", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.STRING))
                .unlockedBy("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.OBSIDIAN))
                .unlockedBy("leather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.LEATHER))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.DIAMOND))
                .save(consumer);
    }

    private void registerGlassStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GLASS_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', BellBlock.GLASS)
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.GLASS))
                .save(consumer);
    }

    private void registerAppleJuice(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftFoods.APPLE_JUICE)
                .requires(ItemNameBlockItem.APPLE)
                .requires(UtilcraftItems.JUICER)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.JUICER))
                .unlockedBy("apple", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.APPLE))
                .save(consumer);
    }

    private void registerCobblestone(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(BellBlock.COBBLESTONE, 9)
                .requires(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("compressed_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.COMPRESSED_COBBLESTONE))
                .save(consumer);
    }

    private void registerRoseQuartz(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftItems.ROSE_QUARTZ, 9)
                .requires(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_block", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.ROSE_QUARTZ_BLOCK))
                .save(consumer);
    }

    private void registerSakuraButton(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_BUTTON)
                .requires(UtilcraftBlocks.SAKURA_PLANKS)
                .group("wooden_button")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS))
                .save(consumer);
    }

    private void registerSakuraPlanks(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_PLANKS, 4)
                .requires(UtilcraftBlocks.SAKURA_LOG)
                .group("planks")
                .unlockedBy("sakura_log", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_LOG))
                .save(consumer);
    }

    private void registerSweetBerryJuice(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftFoods.SWEET_BERRY_JUICE)
                .requires(ItemNameBlockItem.SWEET_BERRIES)
                .requires(UtilcraftItems.JUICER)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.JUICER))
                .unlockedBy("sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.SWEET_BERRIES))
                .save(consumer);
    }

    private void registerLeather(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ItemNameBlockItem.LEATHER, 5)
                .requires(ItemNameBlockItem.SADDLE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("saddle", InventoryChangeTrigger.TriggerInstance.hasItems(ItemNameBlockItem.SADDLE))
                .save(consumer);
    }

    private void registerBaguette(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(FireworkRocketRecipe.of(UtilcraftItems.FLOUR), UtilcraftFoods.BAGUETTE, 0.15f, 200)
                .unlockedBy("flour", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.FLOUR))
                .save(consumer);
    }

    private void registerSilverIngot(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(FireworkRocketRecipe.of(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 200)
                .unlockedBy("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SILVER_ORE))
                .save(consumer);

        SimpleCookingRecipeBuilder.blasting(FireworkRocketRecipe.of(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 100)
                .unlockedBy("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SILVER_ORE))
                .save(consumer, new ResourceLocation(Utilcraft.MOD_ID, UtilcraftItems.SILVER_INGOT.getRegistryName().getPath()+"_from_blasting"));
    }

    private void registerTest(Consumer<FinishedRecipe> consumer) {
        SushiMakerRecipeBuilder.sushiMakerRecipe(UtilcraftBlocks.SUSHI_MAKER)
                .patternLine("****")
                .patternLine("#  #")
                .patternLine("#  #")
                .patternLine("****")
                .key('#', BellBlock.COBBLESTONE)
                .key('*', BellBlock.OBSIDIAN)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.COBBLESTONE))
                .addCriterion("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(BellBlock.OBSIDIAN))
                .build(consumer);
    }
}