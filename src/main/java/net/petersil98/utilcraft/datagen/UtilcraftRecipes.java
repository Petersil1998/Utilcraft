package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.recipes.SushiMakerRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class UtilcraftRecipes extends RecipeProvider {

    public UtilcraftRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
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
        ShapedRecipeBuilder.shaped(UtilcraftItems.BUTCHERS_KNIFE.get())
                .pattern("*")
                .pattern("#")
                .pattern("-")
                .define('*', UtilcraftItems.SILVER_INGOT.get())
                .define('#', Items.DRIED_KELP)
                .define('-', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.SILVER_INGOT.get()))
                .unlockedBy("dried_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DRIED_KELP))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerCompressedCobblestone(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', Blocks.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
                .save(consumer);
    }

    private void registerDisenchantmentTable(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.DISENCHANTMENT_TABLE.get())
                .pattern(" # ")
                .pattern("+*+")
                .pattern("***")
                .define('#', Items.BOOK)
                .define('+', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Blocks.OBSIDIAN)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("book", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOOK))
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN))
                .save(consumer);
    }

    private void registerFlour(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.FLOUR.get())
                .pattern("##")
                .define('#', Items.WHEAT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("wheat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHEAT))
                .save(consumer);
    }

    private void registerGoldBrick(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_BRICK.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', Items.GOLD_INGOT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(consumer);
    }

    private void registerGoldSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_SLAB.get(), 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK.get()))
                .save(consumer);
    }

    private void registerGoldStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK.get()))
                .save(consumer);
    }

    private void registerGoldWall(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GOLD_WALL.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.GOLD_BRICK.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK.get()))
                .save(consumer);
    }

    private void registerJuicer(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.JUICER.get())
                .pattern(" # ")
                .pattern("---")
                .define('#', Items.IRON_INGOT)
                .define('-', Blocks.SMOOTH_STONE_SLAB)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .unlockedBy("smooth_stone_slab", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.SMOOTH_STONE_SLAB))
                .save(consumer);
    }

    private void registerRedstoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_SLAB.get())
                .pattern("###")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRedstoneStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.REDSTONE_STAIRS.get())
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerRoseQuartzAxe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_AXE.get())
                .pattern("##")
                .pattern("#*")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzBlock(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .save(consumer);
    }

    private void registerRoseQuartzBoots(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .save(consumer);
    }

    private void registerRoseQuartzChestplate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .save(consumer);
    }

    private void registerRoseQuartzHelmet(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HELMET.get())
                .pattern("###")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .save(consumer);
    }

    private void registerRoseQuartzHoe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_HOE.get())
                .pattern("##")
                .pattern(" *")
                .pattern(" *")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzLeggings(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_LEGGINGS.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .save(consumer);
    }

    private void registerRoseQuartzPickaxe(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_PICKAXE.get())
                .pattern("###")
                .pattern(" * ")
                .pattern(" * ")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzShovel(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SHOVEL.get())
                .pattern("#")
                .pattern("*")
                .pattern("*")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Items.STICK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerRoseQuartzSuperHammer(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_PICKAXE.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ_PICKAXE.get()))
                .save(consumer);
    }

    private void registerRoseQuartzSuperShovel(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftItems.ROSE_QUARTZ_SHOVEL.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_shovel", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ_SHOVEL.get()))
                .save(consumer);
    }

    private void registerRoseQuartzSword(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.ROSE_QUARTZ_SWORD.get())
                .pattern(" # ")
                .pattern(" # ")
                .pattern("-*-")
                .define('#', UtilcraftItems.ROSE_QUARTZ.get())
                .define('*', Items.STICK)
                .define('-', Blocks.ROSE_BUSH)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .unlockedBy("rose_bush", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.ROSE_BUSH))
                .save(consumer);
    }

    private void registerSakuraDoor(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_DOOR.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_door")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSakuraFence(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE.get())
                .pattern("W#W")
                .pattern("W#W")
                .define('#', Items.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_fence")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraFenceGate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_FENCE_GATE.get())
                .pattern("#W#")
                .pattern("#W#")
                .define('#', Items.STICK)
                .define('W', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_fence_gate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraPressurePlate(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get())
                .pattern("##")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_pressure_plate")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSakuraSign(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SIGN.get(), 3)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .define('X', Items.STICK)
                .group("sign")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);
    }

    private void registerSakuraSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_SLAB.get(), 6)
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_slab")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSakuraStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_stairs")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSakuraTrapdoor(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_trapdoor")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSecureChest(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SECURE_CHEST.get())
                .pattern("###")
                .pattern("#*#")
                .pattern("###")
                .define('#', Ingredient.of(ItemTags.PLANKS))
                .define('*', UtilcraftItems.SILVER_INGOT.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("planks", has(ItemTags.PLANKS))
                .unlockedBy("silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.SILVER_INGOT.get()))
                .save(consumer);
    }

    private void registerSideAcaciaSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_ACACIA_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.ACACIA_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("acacia_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.ACACIA_PLANKS))
                .save(consumer);
    }

    private void registerSideBirchSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_BIRCH_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.BIRCH_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("birch_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.BIRCH_PLANKS))
                .save(consumer);
    }

    private void registerSideCobblestoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.COBBLESTONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
                .save(consumer);
    }

    private void registerSideDarkOakSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.DARK_OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("dark_oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.DARK_OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideGoldSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_GOLD_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.GOLD_BRICK.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("gold_brick", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.GOLD_BRICK.get()))
                .save(consumer);
    }

    private void registerSideJungleSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.JUNGLE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("jungle_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.JUNGLE_PLANKS))
                .save(consumer);
    }

    private void registerSideOakSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_OAK_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.OAK_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("oak_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OAK_PLANKS))
                .save(consumer);
    }

    private void registerSideRedstoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.REDSTONE_BLOCK)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("redstone_block", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);
    }

    private void registerSideSakuraSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_SAKURA_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', UtilcraftBlocks.SAKURA_PLANKS.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSideSpruceSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.SPRUCE_PLANKS)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("spruce_planks", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.SPRUCE_PLANKS))
                .save(consumer);
    }

    private void registerSideStoneSlab(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.SIDE_STONE_SLAB.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', Blocks.STONE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.STONE))
                .save(consumer);
    }

    private void registerTNTFinder(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TNT_FINDER.get())
                .pattern(" + ")
                .pattern("+#+")
                .pattern(" + ")
                .define('#', Items.COMPASS)
                .define('+', Blocks.TNT)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COMPASS))
                .unlockedBy("tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.TNT))
                .save(consumer);
    }

    private void registerTravelersBackpack(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftItems.TRAVELERS_BACKPACK.get())
                .pattern("*X*")
                .pattern("-#-")
                .pattern("000")
                .define('#', Blocks.BARREL)
                .define('-', Items.STRING)
                .define('0', Blocks.OBSIDIAN)
                .define('*', Items.LEATHER)
                .define('X', Items.DIAMOND)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("barrel", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.BARREL))
                .unlockedBy("string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
                .unlockedBy("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN))
                .unlockedBy("leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(consumer);
    }

    private void registerGlassStairs(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(UtilcraftBlocks.GLASS_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Blocks.GLASS)
                .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.GLASS))
                .save(consumer);
    }

    private void registerAppleJuice(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftItems.APPLE_JUICE.get())
                .requires(Items.APPLE)
                .requires(UtilcraftItems.JUICER.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.JUICER.get()))
                .unlockedBy("apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE))
                .save(consumer);
    }

    private void registerCobblestone(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Blocks.COBBLESTONE, 9)
                .requires(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("compressed_cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get()))
                .save(consumer);
    }

    private void registerRoseQuartz(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftItems.ROSE_QUARTZ.get(), 9)
                .requires(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("rose_quartz_block", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get()))
                .save(consumer);
    }

    private void registerSakuraButton(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_BUTTON.get())
                .requires(UtilcraftBlocks.SAKURA_PLANKS.get())
                .group("wooden_button")
                .unlockedBy("sakura_planks", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_PLANKS.get()))
                .save(consumer);
    }

    private void registerSakuraPlanks(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftBlocks.SAKURA_PLANKS.get(), 4)
                .requires(UtilcraftBlocks.SAKURA_LOG.get())
                .group("planks")
                .unlockedBy("sakura_log", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SAKURA_LOG.get()))
                .save(consumer);
    }

    private void registerSweetBerryJuice(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(UtilcraftItems.SWEET_BERRY_JUICE.get())
                .requires(Items.SWEET_BERRIES)
                .requires(UtilcraftItems.JUICER.get())
                .group(Utilcraft.MOD_ID)
                .unlockedBy("juicer", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.JUICER.get()))
                .unlockedBy("sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SWEET_BERRIES))
                .save(consumer);
    }

    private void registerLeather(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Items.LEATHER, 5)
                .requires(Items.SADDLE)
                .group(Utilcraft.MOD_ID)
                .unlockedBy("saddle", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SADDLE))
                .save(consumer);
    }

    private void registerBaguette(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UtilcraftItems.FLOUR.get()), UtilcraftItems.BAGUETTE.get(), 0.15f, 200)
                .unlockedBy("flour", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.FLOUR.get()))
                .save(consumer);
    }

    private void registerSilverIngot(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UtilcraftBlocks.SILVER_ORE.get()), UtilcraftItems.SILVER_INGOT.get(), 0.8f, 200)
                .unlockedBy("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SILVER_ORE.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UtilcraftBlocks.SILVER_ORE.get()), UtilcraftItems.SILVER_INGOT.get(), 0.8f, 100)
                .unlockedBy("silver_ore", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftBlocks.SILVER_ORE.get()))
                .save(consumer, new ResourceLocation(Utilcraft.MOD_ID, UtilcraftItems.SILVER_INGOT.getId().getPath()+"_from_blasting"));
    }

    private void registerTest(Consumer<FinishedRecipe> consumer) {
        SushiMakerRecipeBuilder.sushiMakerRecipe(UtilcraftBlocks.SUSHI_MAKER.get())
                .patternLine("****")
                .patternLine("#  #")
                .patternLine("#  #")
                .patternLine("****")
                .key('#', Blocks.COBBLESTONE)
                .key('*', Blocks.OBSIDIAN)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
                .addCriterion("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN))
                .build(consumer);
    }
}