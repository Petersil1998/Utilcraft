package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SushiMaker;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.utils.BlockItemUtils;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.TwistingVinesPlant;
import net.minecraft.world.level.block.VineBlock;

public class UtilcraftBlockStates extends BlockStateProvider {

    public UtilcraftBlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(UtilcraftBlocks.COMPRESSED_COBBLESTONE);
        simpleBlock(UtilcraftBlocks.DISENCHANTMENT_TABLE);
        simpleBlock(UtilcraftBlocks.GOLD_BRICK);
        registerSlab(UtilcraftBlocks.GOLD_SLAB, UtilcraftBlocks.GOLD_BRICK);
        registerStairs(UtilcraftBlocks.GOLD_STAIRS, UtilcraftBlocks.GOLD_BRICK);
        registerWall(UtilcraftBlocks.GOLD_WALL, UtilcraftBlocks.GOLD_BRICK);
        registerSlab(UtilcraftBlocks.REDSTONE_SLAB, BellBlock.REDSTONE_BLOCK);
        registerStairs(UtilcraftBlocks.REDSTONE_STAIRS, BellBlock.REDSTONE_BLOCK);
        simpleBlock(UtilcraftBlocks.ROSE_QUARTZ_BLOCK);
        simpleBlock(UtilcraftBlocks.ROSE_QUARTZ_ORE);
        registerButton(UtilcraftBlocks.SAKURA_BUTTON, UtilcraftBlocks.SAKURA_PLANKS);
        registerDoor(UtilcraftBlocks.SAKURA_DOOR, UtilcraftBlocks.SAKURA_DOOR);
        registerFence(UtilcraftBlocks.SAKURA_FENCE, UtilcraftBlocks.SAKURA_PLANKS);
        registerFenceGate(UtilcraftBlocks.SAKURA_FENCE_GATE, UtilcraftBlocks.SAKURA_PLANKS);
        simpleBlock(UtilcraftBlocks.SAKURA_LEAVES);
        registerLog(UtilcraftBlocks.SAKURA_LOG);
        simpleBlock(UtilcraftBlocks.SAKURA_PLANKS);
        registerPressurePlate(UtilcraftBlocks.SAKURA_PRESSURE_PLATE, UtilcraftBlocks.SAKURA_PLANKS);
        registerSapling(UtilcraftBlocks.SAKURA_SAPLING);
        registerSign(UtilcraftBlocks.SAKURA_SIGN, UtilcraftBlocks.SAKURA_PLANKS);
        registerSlab(UtilcraftBlocks.SAKURA_SLAB, UtilcraftBlocks.SAKURA_PLANKS);
        registerStairs(UtilcraftBlocks.SAKURA_STAIRS, UtilcraftBlocks.SAKURA_PLANKS);
        registerTrapdoor(UtilcraftBlocks.SAKURA_TRAPDOOR);
        registerWallSign(UtilcraftBlocks.SAKURA_WALL_SIGN, UtilcraftBlocks.SAKURA_SIGN, UtilcraftBlocks.SAKURA_PLANKS);
        registerChest(UtilcraftBlocks.SECURE_CHEST, UtilcraftBlocks.SAKURA_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_ACACIA_SLAB, BellBlock.ACACIA_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_BIRCH_SLAB, BellBlock.BIRCH_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB, BellBlock.COBBLESTONE);
        registerSideSlab(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB, BellBlock.DARK_OAK_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_GOLD_SLAB, UtilcraftBlocks.GOLD_BRICK);
        registerSideSlab(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB, BellBlock.JUNGLE_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_OAK_SLAB, BellBlock.OAK_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB, BellBlock.REDSTONE_BLOCK);
        registerSideSlab(UtilcraftSideSlabs.SIDE_SAKURA_SLAB, UtilcraftBlocks.SAKURA_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB, BellBlock.SPRUCE_PLANKS);
        registerSideSlab(UtilcraftSideSlabs.SIDE_STONE_SLAB, BellBlock.STONE);
        simpleBlock(UtilcraftBlocks.SILVER_ORE);
        registerSushiMaker();
        registerStairs(UtilcraftBlocks.GLASS_STAIRS, BellBlock.GLASS);
        registerPottedBlock(UtilcraftBlocks.POTTED_SAKURA_SAPLING);
        simpleBlock(UtilcraftBlocks.SILVER_BLOCK);
    }

    private void registerSlab(SignBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        slabBlock(block, location, location);
    }

    private void registerStairs(SpreadingSnowyDirtBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        stairsBlock(block, location);
    }

    private void registerWall(TwistingVinesPlant block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        wallBlock(block, location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/wall_inventory"), "wall", location);
    }

    private void registerButton(BubbleColumnBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile button = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/button"), location);
        ModelFile pressed = models().singleTexture(BlockItemUtils.name(block)+"_pressed", mcLoc(ModelProvider.BLOCK_FOLDER+"/button_pressed"), location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/button_inventory"), location);
        getVariantBuilder(block).forAllStates(blockState -> {
            Direction facing = blockState.getValue(BubbleColumnBlock.FACING);
            BlockPredicate face = blockState.getValue(BubbleColumnBlock.FACE);
            boolean powered = blockState.getValue(BubbleColumnBlock.POWERED);
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

    private void registerDoor(DiodeBlock block, BeetrootBlock texture) {
        ResourceLocation bottom = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+"_bottom");
        ResourceLocation top = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture)+"_top");
        doorBlock(block, bottom, top);
    }

    private void registerFence(FaceAttachedHorizontalDirectionalBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        fenceBlock(block, location);
        models().singleTexture(BlockItemUtils.name(block)+"_inventory", mcLoc(ModelProvider.BLOCK_FOLDER+"/fence_inventory"), location);
    }

    private void registerFenceGate(FallingBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        fenceGateBlock(block, location);
    }

    private void registerLog(RepeaterBlock block) {
        logBlock(block);
    }

    private void registerPressurePlate(PotatoBlock block, BeetrootBlock texture) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile pressurePlateUp = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/pressure_plate_up"), location);
        ModelFile pressurePlateDown = models().singleTexture(BlockItemUtils.name(block)+"_down", mcLoc(ModelProvider.BLOCK_FOLDER+"/pressure_plate_down"), location);
        getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder()
                .modelFile(blockState.getValue(PotatoBlock.POWERED) ? pressurePlateDown : pressurePlateUp)
                .build());
    }

    private void registerSapling(RotatedPillarBlock block) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(block), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(block));
        simpleBlock(block, ConfiguredModel.builder().modelFile(models().cross(BlockItemUtils.name(block), location)).build());
    }

    private void registerSign(SeaPickleBlock block, BeetrootBlock particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerTrapdoor(TargetBlock block) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(block), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(block));
        trapdoorBlock(block, location, false);
    }

    private void registerWallSign(VineBlock block, SeaPickleBlock signBlock, BeetrootBlock particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(signBlock), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerChest(BeetrootBlock block, BeetrootBlock particles) {
        ResourceLocation location = new ResourceLocation(BlockItemUtils.namespace(particles), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(particles));
        ModelFile particle = models().singleTexture(BlockItemUtils.name(block), mcLoc(ModelProvider.BLOCK_FOLDER+"/block"), "particle", location);
        simpleBlock(block, ConfiguredModel.builder().modelFile(particle).build());
    }

    private void registerSideSlab(SideSlabBlock block, BeetrootBlock texture) {
        ResourceLocation full = new ResourceLocation(BlockItemUtils.namespace(texture), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(texture));
        ModelFile slabFile = models().withExistingParent(BlockItemUtils.name(block), new ResourceLocation(Utilcraft.MOD_ID, ModelProvider.BLOCK_FOLDER+"/"+SideSlabBlock.NAME))
                .texture("side", full)
                .texture("bottom", full)
                .texture("top", full);
        getVariantBuilder(block)
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.EAST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(90).rotationY(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.WEST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(270).rotationY(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.SOUTH).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(90).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.NORTH).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.DOUBLE).addModels(new ConfiguredModel(models().getExistingFile(full)));
    }

    private void registerSushiMaker() {
        ResourceLocation corner = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SUSHI_MAKER), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER)+"_corner");
        ResourceLocation front = new ResourceLocation(BlockItemUtils.namespace(UtilcraftBlocks.SUSHI_MAKER), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER)+"_front");
        ModelFile modelFile = models().withExistingParent(BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER), "cube")
                .texture("corner", corner)
                .texture("front", front)
                .element().face(Direction.DOWN).texture("#corner").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).end().end()
                .element().face(Direction.UP).texture("#corner").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).end().end()
                .element().face(Direction.NORTH).texture("#front").end().end()
                .element().face(Direction.SOUTH).texture("#front").end().end()
                .element().face(Direction.EAST).texture("#corner").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end().end()
                .element().face(Direction.WEST).texture("#corner").end().end();
        getVariantBuilder(UtilcraftBlocks.SUSHI_MAKER).forAllStates(blockState -> {
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
        models().cubeAll(BlockItemUtils.name(UtilcraftBlocks.SUSHI_MAKER)+"_inventory", front);
    }

    private void registerPottedBlock(FireBlock block) {
        BeetrootBlock realBlock = block.getContent();
        ResourceLocation plant = new ResourceLocation(BlockItemUtils.namespace(realBlock), ModelProvider.BLOCK_FOLDER +"/"+BlockItemUtils.name(realBlock));
        ModelFile modelFile = models().withExistingParent(BlockItemUtils.name(block), "flower_pot_cross")
                .texture("plant", plant);
        getVariantBuilder(block).partialState().addModels(ConfiguredModel.builder().modelFile(modelFile).build());
    }
}
