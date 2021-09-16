package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SushiMaker;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.utils.BlockItemUtils;

public class UtilcraftBlockStates extends BlockStateProvider {

    public UtilcraftBlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get());
        simpleBlock(UtilcraftBlocks.DISENCHANTMENT_TABLE.get());
        simpleBlock(UtilcraftBlocks.GOLD_BRICK.get());
        registerSlab(UtilcraftBlocks.GOLD_SLAB.get(), UtilcraftBlocks.GOLD_BRICK.get());
        registerStairs(UtilcraftBlocks.GOLD_STAIRS.get(), UtilcraftBlocks.GOLD_BRICK.get());
        registerWall(UtilcraftBlocks.GOLD_WALL.get(), UtilcraftBlocks.GOLD_BRICK.get());
        registerSlab(UtilcraftBlocks.REDSTONE_SLAB.get(), Blocks.REDSTONE_BLOCK);
        registerStairs(UtilcraftBlocks.REDSTONE_STAIRS.get(), Blocks.REDSTONE_BLOCK);
        simpleBlock(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get());
        simpleBlock(UtilcraftBlocks.ROSE_QUARTZ_ORE.get());
        registerButton(UtilcraftBlocks.SAKURA_BUTTON.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerDoor(UtilcraftBlocks.SAKURA_DOOR.get(), UtilcraftBlocks.SAKURA_DOOR.get());
        registerFence(UtilcraftBlocks.SAKURA_FENCE.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerFenceGate(UtilcraftBlocks.SAKURA_FENCE_GATE.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        simpleBlock(UtilcraftBlocks.SAKURA_LEAVES.get());
        registerLog(UtilcraftBlocks.SAKURA_LOG.get());
        simpleBlock(UtilcraftBlocks.SAKURA_PLANKS.get());
        registerPressurePlate(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerSapling(UtilcraftBlocks.SAKURA_SAPLING.get());
        registerSign(UtilcraftBlocks.SAKURA_SIGN.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerSlab(UtilcraftBlocks.SAKURA_SLAB.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerStairs(UtilcraftBlocks.SAKURA_STAIRS.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerTrapdoor(UtilcraftBlocks.SAKURA_TRAPDOOR.get());
        registerWallSign(UtilcraftBlocks.SAKURA_WALL_SIGN.get(), UtilcraftBlocks.SAKURA_SIGN.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerChest(UtilcraftBlocks.SECURE_CHEST.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerSideSlab(UtilcraftBlocks.SIDE_ACACIA_SLAB.get(), Blocks.ACACIA_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_BIRCH_SLAB.get(), Blocks.BIRCH_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get(), Blocks.COBBLESTONE);
        registerSideSlab(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get(), Blocks.DARK_OAK_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_GOLD_SLAB.get(), UtilcraftBlocks.GOLD_BRICK.get());
        registerSideSlab(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get(), Blocks.JUNGLE_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_OAK_SLAB.get(), Blocks.OAK_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get(), Blocks.REDSTONE_BLOCK);
        registerSideSlab(UtilcraftBlocks.SIDE_SAKURA_SLAB.get(), UtilcraftBlocks.SAKURA_PLANKS.get());
        registerSideSlab(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get(), Blocks.SPRUCE_PLANKS);
        registerSideSlab(UtilcraftBlocks.SIDE_STONE_SLAB.get(), Blocks.STONE);
        simpleBlock(UtilcraftBlocks.SILVER_ORE.get());
        registerSushiMaker();
        registerStairs(UtilcraftBlocks.GLASS_STAIRS.get(), Blocks.GLASS);
        registerPottedBlock(UtilcraftBlocks.POTTED_SAKURA_SAPLING.get());
        simpleBlock(UtilcraftBlocks.SILVER_BLOCK.get());
    }

    private void registerSlab(SlabBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        slabBlock(block, location, location);
    }

    private void registerStairs(StairsBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        stairsBlock(block, location);
    }

    private void registerWall(WallBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        wallBlock(block, location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/wall_inventory"), "wall", location);
    }

    private void registerButton(AbstractButtonBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile button = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/button"), location);
        ModelFile pressed = models().singleTexture(BlockItemUtils.name(block)+"_pressed", mcLoc(ModelProvider.BLOCK_FOLDER+"/button_pressed"), location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/button_inventory"), location);
        getVariantBuilder(block).forAllStates(blockState -> {
            Direction facing = blockState.getValue(AbstractButtonBlock.FACING);
            AttachFace face = blockState.getValue(AbstractButtonBlock.FACE);
            boolean powered = blockState.getValue(AbstractButtonBlock.POWERED);
            int yRot = 0;
            int xRot = 0;
            boolean uvlock = false;
            switch (facing) {
                case EAST: {
                    yRot = 270;
                    break;
                }
                case WEST: {
                    yRot = 90;
                    break;
                }
                case NORTH: {
                    yRot = 180;
                    break;
                }
            }
            switch (face) {
                case WALL: {
                    xRot = 90;
                    uvlock = true;
                    break;
                }
                case CEILING: {
                    xRot = 180;
                    break;
                }
            }
            return ConfiguredModel.builder()
                    .modelFile(powered ? pressed : button)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .uvLock(uvlock)
                    .build();
        });
    }

    private void registerDoor(DoorBlock block, Block texture) {
        ResourceLocation bottom = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+"_bottom");
        ResourceLocation top = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+"_top");
        doorBlock(block, bottom, top);
    }

    private void registerFence(FenceBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        fenceBlock(block, location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/fence_inventory"), location);
    }

    private void registerFenceGate(FenceGateBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        fenceGateBlock(block, location);
    }

    private void registerLog(RotatedPillarBlock block) {
        logBlock(block);
    }

    private void registerPressurePlate(PressurePlateBlock block, Block texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile pressurePlateUp = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/pressure_plate_up"), location);
        ModelFile pressurePlateDown = models().singleTexture(BlockItemUtils.name(block)+"_down", mcLoc(ModelProvider.BLOCK_FOLDER+"/pressure_plate_down"), location);
        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(blockState.getValue(PressurePlateBlock.POWERED) ? pressurePlateDown : pressurePlateUp)
                .build());
    }

    private void registerSapling(SaplingBlock block) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(block), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(block));
        simpleBlock(block, ConfiguredModel.builder().modelFile(models().cross(BlockItemUtils.name(block), location)).build());
    }

    private void registerSign(AbstractSignBlock block, Block particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerTrapdoor(TrapDoorBlock block) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(block), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(block));
        trapdoorBlock(block, location, false);
    }

    private void registerWallSign(WallSignBlock block, AbstractSignBlock signBlock, Block particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(signBlock), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerChest(Block block, Block particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerSideSlab(SideSlabBlock block, Block texture) {
        ResourceLocation full = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile slabFile = models().withExistingParent(BlockItemUtils.name(block), new ResourceLocation(Utilcraft.MOD_ID, ModelProvider.BLOCK_FOLDER+"/"+SideSlabBlock.NAME))
                .texture("side", full)
                .texture("bottom", full)
                .texture("top", full);
        getVariantBuilder(block)
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.EAST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationY(90).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.WEST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationY(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.SOUTH).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationY(180).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.NORTH).addModels(ConfiguredModel.builder().modelFile(slabFile).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.DOUBLE).addModels(new ConfiguredModel(models().getExistingFile(full)));
    }

    private void registerSushiMaker() {
        ResourceLocation corner = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SUSHI_MAKER.get()), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER.get())+"_corner");
        ResourceLocation front = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SUSHI_MAKER.get()), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER.get())+"_front");
        ModelFile modelFile = models().withExistingParent(BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER.get()), "cube")
                .texture("corner", corner)
                .texture("front", front)
                .element().face(Direction.DOWN).texture("#corner").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).end().end()
                .element().face(Direction.UP).texture("#corner").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).end().end()
                .element().face(Direction.NORTH).texture("#front").end().end()
                .element().face(Direction.SOUTH).texture("#front").end().end()
                .element().face(Direction.EAST).texture("#corner").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end().end()
                .element().face(Direction.WEST).texture("#corner").end().end();
        getVariantBuilder(UtilcraftBlocks.SUSHI_MAKER.get()).forAllStates(blockState -> {
            Direction facing = blockState.getValue(SushiMaker.FACING);
            int rotX = 0;
            int rotY = 0;
            switch (facing) {
                case EAST: {
                    rotY = 90;
                    break;
                }
                case SOUTH: {
                    rotX = 180;
                    break;
                }
                case WEST: {
                    rotY = 270;
                    break;
                }
            }
            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationX(rotX)
                    .rotationY(rotY)
                    .build();
        });
        models().cubeAll(BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER.get())+"_inventory", front);
    }

    private void registerPottedBlock(FlowerPotBlock block) {
        Block realBlock = block.getContent();
        ResourceLocation plant = new ResourceLocation(BlockItemUtils.namespace(realBlock), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(realBlock));
        ModelFile modelFile = models().withExistingParent(BlockItemUtils.name(block), "flower_pot_cross")
                .texture("plant", plant);
        getVariantBuilder(block).partialState().addModels(ConfiguredModel.builder().modelFile(modelFile).build());
    }
}
