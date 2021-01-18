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

    public UtilcraftRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
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
    }

    private void registerSmeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        registerBaguette(consumer);
        registerSilverIngot(consumer);
    }

    private void registerSushiMakerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerTest(consumer);
    }

    private void registerButchersKnife(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.BUTCHERS_KNIFE)
                .patternLine("*")
                .patternLine("#")
                .patternLine("-")
                .key('*', UtilcraftItems.SILVER_INGOT)
                .key('#', Items.DRIED_KELP)
                .key('-', Items.STICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("silver_ingot", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.SILVER_INGOT))
                .addCriterion("dried_kelp", InventoryChangeTrigger.Instance.forItems(Items.DRIED_KELP))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerCompressedCobblestone(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', Blocks.COBBLESTONE)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }

    private void registerDisenchantmentTable(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.DISENCHANTMENT_TABLE)
                .patternLine(" # ")
                .patternLine("+*+")
                .patternLine("***")
                .key('#', Items.BOOK)
                .key('+', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Blocks.OBSIDIAN)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("book", InventoryChangeTrigger.Instance.forItems(Items.BOOK))
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.forItems(Blocks.OBSIDIAN))
                .build(consumer);
    }

    private void registerFlour(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.FLOUR)
                .patternLine("##")
                .key('#', Items.WHEAT)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("wheat", InventoryChangeTrigger.Instance.forItems(Items.WHEAT))
                .build(consumer);
    }

    private void registerGoldBrick(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.GOLD_BRICK, 4)
                .patternLine("##")
                .patternLine("##")
                .key('#', Items.GOLD_INGOT)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("gold_ingot", InventoryChangeTrigger.Instance.forItems(Items.GOLD_INGOT))
                .build(consumer);
    }

    private void registerGoldSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.GOLD_SLAB, 6)
                .patternLine("###")
                .key('#', UtilcraftBlocks.GOLD_BRICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("gold_brick", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.GOLD_BRICK))
                .build(consumer);
    }

    private void registerGoldStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.GOLD_STAIRS, 4)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', UtilcraftBlocks.GOLD_BRICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("gold_brick", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.GOLD_BRICK))
                .build(consumer);
    }

    private void registerGoldWall(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.GOLD_WALL, 6)
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftBlocks.GOLD_BRICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("gold_brick", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.GOLD_BRICK))
                .build(consumer);
    }

    private void registerJuicer(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.JUICER)
                .patternLine(" # ")
                .patternLine("---")
                .key('#', Items.IRON_INGOT)
                .key('-', Blocks.SMOOTH_STONE_SLAB)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
                .addCriterion("smooth_stone_slab", InventoryChangeTrigger.Instance.forItems(Blocks.SMOOTH_STONE_SLAB))
                .build(consumer);
    }

    private void registerRedstoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.REDSTONE_SLAB)
                .patternLine("###")
                .key('#', Blocks.REDSTONE_BLOCK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("redstone_block", InventoryChangeTrigger.Instance.forItems(Blocks.REDSTONE_BLOCK))
                .build(consumer);
    }

    private void registerRedstoneStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.REDSTONE_STAIRS)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', Blocks.REDSTONE_BLOCK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("redstone_block", InventoryChangeTrigger.Instance.forItems(Blocks.REDSTONE_BLOCK))
                .build(consumer);
    }

    private void registerRoseQuartzAxe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_AXE)
                .patternLine("##")
                .patternLine("#*")
                .patternLine(" *")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Items.STICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerRoseQuartzBlock(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .build(consumer);
    }

    private void registerRoseQuartzBoots(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_BOOTS)
                .patternLine("# #")
                .patternLine("# #")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .build(consumer);
    }

    private void registerRoseQuartzChestplate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE)
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .build(consumer);
    }

    private void registerRoseQuartzHelmet(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_HELMET)
                .patternLine("###")
                .patternLine("# #")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .build(consumer);
    }

    private void registerRoseQuartzHoe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_HOE)
                .patternLine("##")
                .patternLine(" *")
                .patternLine(" *")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Items.STICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerRoseQuartzLeggings(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_LEGGINGS)
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .build(consumer);
    }

    private void registerRoseQuartzPickaxe(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .patternLine("###")
                .patternLine(" * ")
                .patternLine(" * ")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Items.STICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerRoseQuartzShovel(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .patternLine("#")
                .patternLine("*")
                .patternLine("*")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Items.STICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerRoseQuartzSuperHammer(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftItems.ROSE_QUARTZ_PICKAXE)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz_pickaxe", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ_PICKAXE))
                .build(consumer);
    }

    private void registerRoseQuartzSuperShovel(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftItems.ROSE_QUARTZ_SHOVEL)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz_shovel", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ_SHOVEL))
                .build(consumer);
    }

    private void registerRoseQuartzSword(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.ROSE_QUARTZ_SWORD)
                .patternLine(" # ")
                .patternLine(" # ")
                .patternLine("-*-")
                .key('#', UtilcraftItems.ROSE_QUARTZ)
                .key('*', Items.STICK)
                .key('-', Blocks.ROSE_BUSH)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.ROSE_QUARTZ))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .addCriterion("rose_bush", InventoryChangeTrigger.Instance.forItems(Blocks.ROSE_BUSH))
                .build(consumer);
    }

    private void registerSakuraDoor(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_DOOR)
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_door")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSakuraFence(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_FENCE)
                .patternLine("W#W")
                .patternLine("W#W")
                .key('#', Items.STICK)
                .key('W', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_fence")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .addCriterion("sticks", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerSakuraFenceGate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_FENCE_GATE)
                .patternLine("#W#")
                .patternLine("#W#")
                .key('#', Items.STICK)
                .key('W', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_fence_gate")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .addCriterion("sticks", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerSakuraPressurePlate(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_PRESSURE_PLATE)
                .patternLine("##")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_pressure_plate")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSakuraSign(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_SIGN, 3)
                .patternLine("###")
                .patternLine("###")
                .patternLine(" X ")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .key('X', Items.STICK)
                .setGroup("sign")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .addCriterion("stick", InventoryChangeTrigger.Instance.forItems(Items.STICK))
                .build(consumer);
    }

    private void registerSakuraSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_SLAB, 6)
                .patternLine("###")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_slab")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSakuraStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_STAIRS, 4)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_stairs")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSakuraTrapdoor(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SAKURA_TRAPDOOR, 2)
                .patternLine("###")
                .patternLine("###")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_trapdoor")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSecureChest(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.SECURE_CHEST)
                .patternLine("###")
                .patternLine("#*#")
                .patternLine("###")
                .key('#', Ingredient.fromTag(ItemTags.PLANKS))
                .key('*', UtilcraftItems.SILVER_INGOT)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("planks", hasItem(ItemTags.PLANKS))
                .addCriterion("silver_ingot", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.SILVER_INGOT))
                .build(consumer);
    }

    private void registerSideAcaciaSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.ACACIA_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("acacia_planks", InventoryChangeTrigger.Instance.forItems(Blocks.ACACIA_PLANKS))
                .build(consumer);
    }

    private void registerSideBirchSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.BIRCH_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("birch_planks", InventoryChangeTrigger.Instance.forItems(Blocks.BIRCH_PLANKS))
                .build(consumer);
    }

    private void registerSideCobblestoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.COBBLESTONE)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }

    private void registerSideDarkOakSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.DARK_OAK_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("dark_oak_planks", InventoryChangeTrigger.Instance.forItems(Blocks.DARK_OAK_PLANKS))
                .build(consumer);
    }

    private void registerSideGoldSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_GOLD_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', UtilcraftBlocks.GOLD_BRICK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("gold_brick", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.GOLD_BRICK))
                .build(consumer);
    }

    private void registerSideJungleSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.JUNGLE_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("jungle_planks", InventoryChangeTrigger.Instance.forItems(Blocks.JUNGLE_PLANKS))
                .build(consumer);
    }

    private void registerSideOakSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_OAK_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.OAK_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("oak_planks", InventoryChangeTrigger.Instance.forItems(Blocks.OAK_PLANKS))
                .build(consumer);
    }

    private void registerSideRedstoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.REDSTONE_BLOCK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("redstone_block", InventoryChangeTrigger.Instance.forItems(Blocks.REDSTONE_BLOCK))
                .build(consumer);
    }

    private void registerSideSakuraSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSideSpruceSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.SPRUCE_PLANKS)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("spruce_planks", InventoryChangeTrigger.Instance.forItems(Blocks.SPRUCE_PLANKS))
                .build(consumer);
    }

    private void registerSideStoneSlab(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftSideSlabs.SIDE_STONE_SLAB, 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', Blocks.STONE)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("stone", InventoryChangeTrigger.Instance.forItems(Blocks.STONE))
                .build(consumer);
    }

    private void registerTNTFinder(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.TNT_FINDER)
                .patternLine(" + ")
                .patternLine("+#+")
                .patternLine(" + ")
                .key('#', Items.COMPASS)
                .key('+', Blocks.TNT)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("stone", InventoryChangeTrigger.Instance.forItems(Items.COMPASS))
                .addCriterion("tnt", InventoryChangeTrigger.Instance.forItems(Blocks.TNT))
                .build(consumer);
    }

    private void registerTravelersBackpack(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftItems.TRAVELERS_BACKPACK)
                .patternLine("*X*")
                .patternLine("-#-")
                .patternLine("000")
                .key('#', Blocks.BARREL)
                .key('-', Items.STRING)
                .key('0', Blocks.OBSIDIAN)
                .key('*', Items.LEATHER)
                .key('X', Items.DIAMOND)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("barrel", InventoryChangeTrigger.Instance.forItems(Blocks.BARREL))
                .addCriterion("string", InventoryChangeTrigger.Instance.forItems(Items.STRING))
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.forItems(Blocks.OBSIDIAN))
                .addCriterion("leather", InventoryChangeTrigger.Instance.forItems(Items.LEATHER))
                .addCriterion("diamond", InventoryChangeTrigger.Instance.forItems(Items.DIAMOND))
                .build(consumer);
    }

    private void registerGlassStairs(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(UtilcraftBlocks.GLASS_STAIRS, 4)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', Blocks.GLASS)
                .addCriterion("glass", InventoryChangeTrigger.Instance.forItems(Blocks.GLASS))
                .build(consumer);
    }

    private void registerAppleJuice(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(UtilcraftFoods.APPLE_JUICE)
                .addIngredient(Items.APPLE)
                .addIngredient(UtilcraftItems.JUICER)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("juicer", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.JUICER))
                .addCriterion("apple", InventoryChangeTrigger.Instance.forItems(Items.APPLE))
                .build(consumer);
    }

    private void registerCobblestone(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(Blocks.COBBLESTONE, 9)
                .addIngredient(UtilcraftBlocks.COMPRESSED_COBBLESTONE)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("compressed_cobblestone", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.COMPRESSED_COBBLESTONE))
                .build(consumer);
    }

    private void registerRoseQuartz(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(UtilcraftItems.ROSE_QUARTZ, 9)
                .addIngredient(UtilcraftBlocks.ROSE_QUARTZ_BLOCK)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("rose_quartz_block", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.ROSE_QUARTZ_BLOCK))
                .build(consumer);
    }

    private void registerSakuraButton(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(UtilcraftBlocks.SAKURA_BUTTON)
                .addIngredient(UtilcraftBlocks.SAKURA_PLANKS)
                .setGroup("wooden_button")
                .addCriterion("sakura_planks", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_PLANKS))
                .build(consumer);
    }

    private void registerSakuraPlanks(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(UtilcraftBlocks.SAKURA_PLANKS, 4)
                .addIngredient(UtilcraftBlocks.SAKURA_LOG)
                .setGroup("planks")
                .addCriterion("sakura_log", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SAKURA_LOG))
                .build(consumer);
    }

    private void registerSweetBerryJuice(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(UtilcraftFoods.SWEET_BERRY_JUICE)
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(UtilcraftItems.JUICER)
                .setGroup(Utilcraft.MOD_ID)
                .addCriterion("juicer", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.JUICER))
                .addCriterion("sweet_berries", InventoryChangeTrigger.Instance.forItems(Items.SWEET_BERRIES))
                .build(consumer);
    }

    private void registerBaguette(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(UtilcraftItems.FLOUR), UtilcraftFoods.BAGUETTE, 0.15f, 200)
                .addCriterion("flour", InventoryChangeTrigger.Instance.forItems(UtilcraftItems.FLOUR))
                .build(consumer);
    }

    private void registerSilverIngot(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 200)
                .addCriterion("silver_ore", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SILVER_ORE))
                .build(consumer);

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(UtilcraftBlocks.SILVER_ORE), UtilcraftItems.SILVER_INGOT, 0.8f, 100)
                .addCriterion("silver_ore", InventoryChangeTrigger.Instance.forItems(UtilcraftBlocks.SILVER_ORE))
                .build(consumer, new ResourceLocation(Utilcraft.MOD_ID, UtilcraftItems.SILVER_INGOT.getRegistryName().getPath()+"_from_blasting"));
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
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .addCriterion("obsidian", InventoryChangeTrigger.Instance.forItems(Blocks.OBSIDIAN))
                .build(consumer);
    }
}