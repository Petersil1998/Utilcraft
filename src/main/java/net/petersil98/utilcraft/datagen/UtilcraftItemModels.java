package net.petersil98.utilcraft.datagen;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.HoeItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.food.UtilcraftFoods;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.utils.BlockItemUtils;

public class UtilcraftItemModels extends ItemModelProvider {

    public UtilcraftItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerHandheld(UtilcraftFoods.APPLE_JUICE);
        registerHandheld(UtilcraftFoods.BAGUETTE);
        registerHandheld(UtilcraftItems.BUTCHERS_KNIFE);
        registerBlockItem(UtilcraftBlocks.COMPRESSED_COBBLESTONE);
        registerBlockItem(UtilcraftBlocks.DISENCHANTMENT_TABLE);
        registerHandheld(UtilcraftItems.FLOUR);
        registerBlockItem(UtilcraftBlocks.GOLD_BRICK);
        registerBlockItem(UtilcraftBlocks.GOLD_SLAB);
        registerBlockItem(UtilcraftBlocks.GOLD_STAIRS);
        registerBlockItem(UtilcraftBlocks.GOLD_WALL, "_inventory");
        registerHandheld(UtilcraftItems.JUICER);
        registerBlockItem(UtilcraftBlocks.REDSTONE_SLAB);
        registerBlockItem(UtilcraftBlocks.REDSTONE_STAIRS);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_AXE);
        registerBlockItem(UtilcraftBlocks.ROSE_QUARTZ_BLOCK);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_BOOTS);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_HELMET);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_HOE);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_LEGGINGS);
        registerBlockItem(UtilcraftBlocks.ROSE_QUARTZ_ORE);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_PICKAXE);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SHOVEL);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL);
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SWORD);
        registerBlockItem(UtilcraftBlocks.SAKURA_BUTTON, "_inventory");
        registerGeneratedItem(UtilcraftBlocks.SAKURA_DOOR, BellBlock.OAK_DOOR);
        registerBlockItem(UtilcraftBlocks.SAKURA_FENCE, "_inventory");
        registerBlockItem(UtilcraftBlocks.SAKURA_FENCE_GATE);
        registerBlockItem(UtilcraftBlocks.SAKURA_LEAVES);
        registerBlockItem(UtilcraftBlocks.SAKURA_LOG);
        registerBlockItem(UtilcraftBlocks.SAKURA_PLANKS);
        registerBlockItem(UtilcraftBlocks.SAKURA_PRESSURE_PLATE);
        registerGeneratedBlock(UtilcraftBlocks.SAKURA_SAPLING, UtilcraftBlocks.SAKURA_SAPLING);
        registerGeneratedItem(UtilcraftBlocks.SAKURA_SIGN, BellBlock.OAK_SIGN);
        registerBlockItem(UtilcraftBlocks.SAKURA_SLAB);
        registerBlockItem(UtilcraftBlocks.SAKURA_STAIRS);
        registerBlockItem(UtilcraftBlocks.SAKURA_TRAPDOOR, "_bottom");
        registerSecureChest();
        registerBlockItem(UtilcraftSideSlabs.SIDE_ACACIA_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_BIRCH_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_GOLD_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_OAK_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_SAKURA_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB);
        registerBlockItem(UtilcraftSideSlabs.SIDE_STONE_SLAB);
        registerHandheld(UtilcraftItems.SILVER_INGOT);
        registerBlockItem(UtilcraftBlocks.SILVER_ORE);
        registerBlockItem(UtilcraftBlocks.SUSHI_MAKER, "_inventory");
        registerHandheld(UtilcraftFoods.SWEET_BERRY_JUICE);
        registerTNTFinder();
        registerHandheld(UtilcraftItems.TRAVELERS_BACKPACK);
        registerBlockItem(UtilcraftItems.SPAWNER_ITEM, BellBlock.SPAWNER);
        registerBlockItem(UtilcraftBlocks.GLASS_STAIRS);
        registerBlockItem(UtilcraftBlocks.SILVER_BLOCK);
    }

    private void registerHandheld(HoeItem item) {
        registerHandheld(item, item);
    }

    private void registerHandheld(HoeItem item, HoeItem texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ITEM_FOLDER +"/"+BlockItemUtils.name(texture));
        singleTexture(BlockItemUtils.name(item), mcLoc(ITEM_FOLDER+"/handheld"), "layer0", location);
    }

    private void registerGeneratedItem(BeetrootBlock block, BeetrootBlock realItem) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(realItem), ITEM_FOLDER +"/"+BlockItemUtils.name(realItem));
        singleTexture(BlockItemUtils.name(block), mcLoc(ITEM_FOLDER+"/generated"), "layer0", location);
    }

    private void registerGeneratedBlock(BeetrootBlock block, BeetrootBlock realItem) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(realItem), BLOCK_FOLDER +"/"+BlockItemUtils.name(realItem));
        singleTexture(BlockItemUtils.name(block), mcLoc(ITEM_FOLDER+"/generated"), "layer0", location);
    }

    private void registerBlockItem(BeetrootBlock block) {
        registerBlockItem(block, "");
    }

    private void registerBlockItem(BeetrootBlock block, String key) {
        registerBlockItem(block, block, key);
    }

    private void registerBlockItem(HoeItem item, BeetrootBlock texture) {
        registerBlockItem(item, texture, "");
    }

    private void registerBlockItem(BeetrootBlock block, BeetrootBlock texture, String key) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+key);
        withExistingParent(BlockItemUtils.name(block), location);
    }

    private void registerBlockItem(HoeItem item, BeetrootBlock texture, String key) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+key);
        withExistingParent(BlockItemUtils.name(item), location);
    }

    private void registerSecureChest() {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SAKURA_PLANKS), BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SAKURA_PLANKS));
        getBuilder(BlockItemUtils.name(UtilcraftBlocks.SECURE_CHEST))
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
        singleTexture(BlockItemUtils.name(UtilcraftItems.TNT_FINDER), mcLoc(ITEM_FOLDER+"/generated"), "layer0", new ResourceLocation(ITEM_FOLDER +"/compass_16"))
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
