package net.petersil98.utilcraft.datagen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.ModSideSlabs;
import net.petersil98.utilcraft.food.ModFoods;
import net.petersil98.utilcraft.items.ModItems;
import net.petersil98.utilcraft.utils.BlockItemUtils;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerHandheld(ModFoods.APPLE_JUICE);
        registerHandheld(ModFoods.BAGUETTE);
        registerHandheld(ModItems.BUTCHERS_KNIFE);
        registerBlockItem(ModBlocks.COMPRESSED_COBBLESTONE);
        registerBlockItem(ModBlocks.DISENCHANTMENT_TABLE);
        registerHandheld(ModItems.FLOUR);
        registerBlockItem(ModBlocks.GOLD_BRICK);
        registerBlockItem(ModBlocks.GOLD_SLAB);
        registerBlockItem(ModBlocks.GOLD_STAIRS);
        registerBlockItem(ModBlocks.GOLD_WALL, "_inventory");
        registerHandheld(ModItems.JUICER);
        registerBlockItem(ModBlocks.REDSTONE_SLAB);
        registerBlockItem(ModBlocks.REDSTONE_STAIRS);
        registerHandheld(ModItems.ROSE_QUARTZ);
        registerHandheld(ModItems.ROSE_QUARTZ_AXE);
        registerBlockItem(ModBlocks.ROSE_QUARTZ_BLOCK);
        registerHandheld(ModItems.ROSE_QUARTZ_BOOTS);
        registerHandheld(ModItems.ROSE_QUARTZ_CHESTPLATE);
        registerHandheld(ModItems.ROSE_QUARTZ_HELMET);
        registerHandheld(ModItems.ROSE_QUARTZ_HOE);
        registerHandheld(ModItems.ROSE_QUARTZ_LEGGINGS);
        registerBlockItem(ModBlocks.ROSE_QUARTZ_ORE);
        registerHandheld(ModItems.ROSE_QUARTZ_PICKAXE);
        registerHandheld(ModItems.ROSE_QUARTZ_SHOVEL);
        registerHandheld(ModItems.ROSE_QUARTZ_SUPER_HAMMER);
        registerHandheld(ModItems.ROSE_QUARTZ_SUPER_SHOVEL);
        registerHandheld(ModItems.ROSE_QUARTZ_SWORD);
        registerBlockItem(ModBlocks.SAKURA_BUTTON, "_inventory");
        registerGeneratedItem(ModBlocks.SAKURA_DOOR, Blocks.OAK_DOOR);
        registerBlockItem(ModBlocks.SAKURA_FENCE, "_inventory");
        registerBlockItem(ModBlocks.SAKURA_FENCE_GATE);
        registerBlockItem(ModBlocks.SAKURA_LEAVES);
        registerBlockItem(ModBlocks.SAKURA_LOG);
        registerBlockItem(ModBlocks.SAKURA_PLANKS);
        registerBlockItem(ModBlocks.SAKURA_PRESSURE_PLATE);
        registerGeneratedBlock(ModBlocks.SAKURA_SAPLING, ModBlocks.SAKURA_SAPLING);
        registerGeneratedItem(ModBlocks.SAKURA_SIGN, Blocks.OAK_SIGN);
        registerBlockItem(ModBlocks.SAKURA_SLAB);
        registerBlockItem(ModBlocks.SAKURA_STAIRS);
        registerBlockItem(ModBlocks.SAKURA_TRAPDOOR, "_bottom");
        registerSecureChest();
        registerBlockItem(ModSideSlabs.SIDE_ACACIA_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_BIRCH_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_COBBLESTONE_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_DARK_OAK_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_GOLD_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_JUNGLE_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_OAK_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_REDSTONE_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_SAKURA_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_SPRUCE_SLAB);
        registerBlockItem(ModSideSlabs.SIDE_STONE_SLAB);
        registerHandheld(ModItems.SILVER_INGOT);
        registerBlockItem(ModBlocks.SILVER_ORE);
        registerBlockItem(ModBlocks.SUSHI_MAKER, "_inventory");
        registerHandheld(ModFoods.SWEET_BERRY_JUICE);
        registerTNTFinder();
        registerHandheld(ModItems.TRAVELERS_BACKPACK);
    }

    private void registerHandheld(Item item) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(item), ITEM_FOLDER +"/"+BlockItemUtils.name(item));
        singleTexture(BlockItemUtils.name(item), mcLoc(ITEM_FOLDER+"/handheld"), "layer0", location);
    }

    private void registerGeneratedItem(Block block, Block realItem) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(realItem), ITEM_FOLDER +"/"+BlockItemUtils.name(realItem));
        singleTexture(BlockItemUtils.name(block), mcLoc(ITEM_FOLDER+"/generated"), "layer0", location);
    }

    private void registerGeneratedBlock(Block block, Block realItem) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(realItem), BLOCK_FOLDER +"/"+BlockItemUtils.name(realItem));
        singleTexture(BlockItemUtils.name(block), mcLoc(ITEM_FOLDER+"/generated"), "layer0", location);
    }

    private void registerBlockItem(Block block) {
        registerBlockItem(block, "");
    }

    private void registerBlockItem(Block block, String key) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(block), BLOCK_FOLDER +"/"+BlockItemUtils.name(block)+key);
        withExistingParent(BlockItemUtils.name(block), location);
    }

    private void registerSecureChest() {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(ModBlocks.SAKURA_PLANKS), BLOCK_FOLDER +"/"+BlockItemUtils.name(ModBlocks.SAKURA_PLANKS));
        getBuilder(BlockItemUtils.name(ModBlocks.SECURE_CHEST))
                .parent(new ModelFile.UncheckedModelFile(mcLoc("builtin/entity")))
                .texture("particle", location)
                .transforms()
                .transform(ModelBuilder.Perspective.GUI).rotation(30,45,0).scale(0.625f, 0.625f, 0.625f).end()
                .transform(ModelBuilder.Perspective.GROUND).translation(0,3,0).scale(0.25f, 0.25f, 0.25f).end()
                .transform(ModelBuilder.Perspective.HEAD).rotation(0,180,0).scale(1, 1, 1).end()
                .transform(ModelBuilder.Perspective.FIXED).rotation(0,180,0).scale(0.5f, 0.5f, 0.5f).end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT).rotation(75,315,0).translation(0,2.5f,0).scale(0.375f, 0.375f, 0.375f).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT).rotation(0,315,0).scale(0.4f, 0.4f, 0.4f).end();
    }

    private void registerTNTFinder() {
        ResourceLocation angle = new ResourceLocation("angle");
        singleTexture(BlockItemUtils.name(ModItems.TNT_FINDER), mcLoc(ITEM_FOLDER+"/generated"), "layer0", new ResourceLocation(ITEM_FOLDER +"/compass_16"))
                .override().predicate(angle, 0f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass"), existingFileHelper)).end()
                .override().predicate(angle, 0.015625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_17"), existingFileHelper)).end()
                .override().predicate(angle, 0.046875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_18"), existingFileHelper)).end()
                .override().predicate(angle, 0.078125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_19"), existingFileHelper)).end()
                .override().predicate(angle, 0.109375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_20"), existingFileHelper)).end()
                .override().predicate(angle, 0.140625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_21"), existingFileHelper)).end()
                .override().predicate(angle, 0.171875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_22"), existingFileHelper)).end()
                .override().predicate(angle, 0.203125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_23"), existingFileHelper)).end()
                .override().predicate(angle, 0.234375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_24"), existingFileHelper)).end()
                .override().predicate(angle, 0.265625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_25"), existingFileHelper)).end()
                .override().predicate(angle, 0.296875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_26"), existingFileHelper)).end()
                .override().predicate(angle, 0.328125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_27"), existingFileHelper)).end()
                .override().predicate(angle, 0.359375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_28"), existingFileHelper)).end()
                .override().predicate(angle, 0.390625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_29"), existingFileHelper)).end()
                .override().predicate(angle, 0.421875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_30"), existingFileHelper)).end()
                .override().predicate(angle, 0.453125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_31"), existingFileHelper)).end()
                .override().predicate(angle, 0.484375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_00"), existingFileHelper)).end()
                .override().predicate(angle, 0.515625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_01"), existingFileHelper)).end()
                .override().predicate(angle, 0.546875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_02"), existingFileHelper)).end()
                .override().predicate(angle, 0.578125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_03"), existingFileHelper)).end()
                .override().predicate(angle, 0.609375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_04"), existingFileHelper)).end()
                .override().predicate(angle, 0.640625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_05"), existingFileHelper)).end()
                .override().predicate(angle, 0.671875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_06"), existingFileHelper)).end()
                .override().predicate(angle, 0.703125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_07"), existingFileHelper)).end()
                .override().predicate(angle, 0.734375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_08"), existingFileHelper)).end()
                .override().predicate(angle, 0.765625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_09"), existingFileHelper)).end()
                .override().predicate(angle, 0.796875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_10"), existingFileHelper)).end()
                .override().predicate(angle, 0.828125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_11"), existingFileHelper)).end()
                .override().predicate(angle, 0.859375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_12"), existingFileHelper)).end()
                .override().predicate(angle, 0.890625f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_13"), existingFileHelper)).end()
                .override().predicate(angle, 0.921875f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_14"), existingFileHelper)).end()
                .override().predicate(angle, 0.953125f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass_15"), existingFileHelper)).end()
                .override().predicate(angle, 0.984375f).model(new ItemModelBuilder(new ResourceLocation(ITEM_FOLDER +"/compass"), existingFileHelper)).end();
    }
}
