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
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.utils.BlockItemUtils;

public class UtilcraftItemModels extends ItemModelProvider {

    public UtilcraftItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerHandheld(UtilcraftItems.APPLE_JUICE.get());
        registerHandheld(UtilcraftItems.BAGUETTE.get());
        registerHandheld(UtilcraftItems.BUTCHERS_KNIFE.get());
        registerBlockItem(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get());
        registerBlockItem(UtilcraftBlocks.DISENCHANTMENT_TABLE.get());
        registerHandheld(UtilcraftItems.FLOUR.get());
        registerBlockItem(UtilcraftBlocks.GOLD_BRICK.get());
        registerBlockItem(UtilcraftBlocks.GOLD_SLAB.get());
        registerBlockItem(UtilcraftBlocks.GOLD_STAIRS.get());
        registerBlockItem(UtilcraftBlocks.GOLD_WALL.get(), "_inventory");
        registerHandheld(UtilcraftItems.JUICER.get());
        registerBlockItem(UtilcraftBlocks.REDSTONE_SLAB.get());
        registerBlockItem(UtilcraftBlocks.REDSTONE_STAIRS.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_AXE.get());
        registerBlockItem(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_BOOTS.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_CHESTPLATE.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_HELMET.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_HOE.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_LEGGINGS.get());
        registerBlockItem(UtilcraftBlocks.ROSE_QUARTZ_ORE.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_PICKAXE.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SHOVEL.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SUPER_HAMMER.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SUPER_SHOVEL.get());
        registerHandheld(UtilcraftItems.ROSE_QUARTZ_SWORD.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_BUTTON.get(), "_inventory");
        registerGeneratedItem(UtilcraftBlocks.SAKURA_DOOR.get(), Blocks.OAK_DOOR);
        registerBlockItem(UtilcraftBlocks.SAKURA_FENCE.get(), "_inventory");
        registerBlockItem(UtilcraftBlocks.SAKURA_FENCE_GATE.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_LEAVES.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_LOG.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_PLANKS.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get());
        registerGeneratedBlock(UtilcraftBlocks.SAKURA_SAPLING.get(), UtilcraftBlocks.SAKURA_SAPLING.get());
        registerGeneratedItem(UtilcraftBlocks.SAKURA_SIGN.get(), Blocks.OAK_SIGN);
        registerBlockItem(UtilcraftBlocks.SAKURA_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_STAIRS.get());
        registerBlockItem(UtilcraftBlocks.SAKURA_TRAPDOOR.get(), "_bottom");
        registerSecureChest();
        registerBlockItem(UtilcraftBlocks.SIDE_ACACIA_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_BIRCH_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_GOLD_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_OAK_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_SAKURA_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get());
        registerBlockItem(UtilcraftBlocks.SIDE_STONE_SLAB.get());
        registerHandheld(UtilcraftItems.SILVER_INGOT.get());
        registerBlockItem(UtilcraftBlocks.SILVER_ORE.get());
        registerBlockItem(UtilcraftBlocks.SUSHI_MAKER.get(), "_inventory");
        registerHandheld(UtilcraftItems.SWEET_BERRY_JUICE.get());
        registerTNTFinder();
        registerHandheld(UtilcraftItems.TRAVELERS_BACKPACK.get());
        registerBlockItem(UtilcraftItems.SPAWNER_ITEM.get(), Blocks.SPAWNER);
        registerBlockItem(UtilcraftBlocks.GLASS_STAIRS.get());
        registerBlockItem(UtilcraftBlocks.SILVER_BLOCK.get());
    }

    private void registerHandheld(Item item) {
        registerHandheld(item, item);
    }

    private void registerHandheld(Item item, Item texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ITEM_FOLDER +"/"+BlockItemUtils.name(texture));
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
        registerBlockItem(block, block, key);
    }

    private void registerBlockItem(Item item, Block texture) {
        registerBlockItem(item, texture, "");
    }

    private void registerBlockItem(Block block, Block texture, String key) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+key);
        withExistingParent(BlockItemUtils.name(block), location);
    }

    private void registerBlockItem(Item item, Block texture, String key) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+key);
        withExistingParent(BlockItemUtils.name(item), location);
    }

    private void registerSecureChest() {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SAKURA_PLANKS.get()), BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SAKURA_PLANKS.get()));
        getBuilder(BlockItemUtils.name(UtilcraftBlocks.SECURE_CHEST.get()))
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
        singleTexture(BlockItemUtils.name(UtilcraftItems.TNT_FINDER.get()), mcLoc(ITEM_FOLDER+"/generated"), "layer0", new ResourceLocation(ITEM_FOLDER +"/compass_16"))
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
