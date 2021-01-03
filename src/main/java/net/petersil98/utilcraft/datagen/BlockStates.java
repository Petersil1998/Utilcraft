package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.blocks.custom.SideSlabType;
import net.petersil98.utilcraft.blocks.sideslabs.ModSideSlabs;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.utils.BlockItemUtils;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.COMPRESSED_COBBLESTONE);
        simpleBlock(ModBlocks.DISENCHANTMENT_TABLE);
        simpleBlock(ModBlocks.GOLD_BRICK);
        registerSlab(ModBlocks.GOLD_SLAB, ModBlocks.GOLD_BRICK);
        registerStairs(ModBlocks.GOLD_STAIRS, ModBlocks.GOLD_BRICK);
        registerWall(ModBlocks.GOLD_WALL, ModBlocks.GOLD_BRICK);
        registerSlab(ModBlocks.REDSTONE_SLAB, Blocks.REDSTONE_BLOCK);
        registerStairs(ModBlocks.REDSTONE_STAIRS, Blocks.REDSTONE_BLOCK);
        simpleBlock(ModBlocks.ROSE_QUARTZ_BLOCK);
        simpleBlock(ModBlocks.ROSE_QUARTZ_ORE);
        registerButton(ModBlocks.SAKURA_BUTTON, ModBlocks.SAKURA_PLANKS);
        registerDoor(ModBlocks.SAKURA_DOOR, ModBlocks.SAKURA_DOOR);
        registerFence(ModBlocks.SAKURA_FENCE, ModBlocks.SAKURA_PLANKS);
        registerFenceGate(ModBlocks.SAKURA_FENCE_GATE, ModBlocks.SAKURA_PLANKS);
        simpleBlock(ModBlocks.SAKURA_LEAVES);
        registerLog(ModBlocks.SAKURA_LOG);
        simpleBlock(ModBlocks.SAKURA_PLANKS);
        registerPressurePlate(ModBlocks.SAKURA_PRESSURE_PLATE, ModBlocks.SAKURA_PLANKS);
        registerSapling(ModBlocks.SAKURA_SAPLING);
        registerSign(ModBlocks.SAKURA_SIGN, ModBlocks.SAKURA_PLANKS);
        registerSlab(ModBlocks.SAKURA_SLAB, ModBlocks.SAKURA_PLANKS);
        registerStairs(ModBlocks.SAKURA_STAIRS, ModBlocks.SAKURA_PLANKS);
        registerTrapdoor(ModBlocks.SAKURA_TRAPDOOR);
        registerWallSign(ModBlocks.SAKURA_WALL_SIGN, ModBlocks.SAKURA_SIGN, ModBlocks.SAKURA_PLANKS);
        registerChest(ModBlocks.SECURE_CHEST, ModBlocks.SAKURA_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_ACACIA_SLAB, Blocks.ACACIA_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_BIRCH_SLAB, Blocks.BIRCH_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_COBBLESTONE_SLAB, Blocks.COBBLESTONE);
        registerSideSlab(ModSideSlabs.SIDE_DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_GOLD_SLAB, ModBlocks.GOLD_BRICK);
        registerSideSlab(ModSideSlabs.SIDE_JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_OAK_SLAB, Blocks.OAK_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_REDSTONE_SLAB, Blocks.REDSTONE_BLOCK);
        registerSideSlab(ModSideSlabs.SIDE_SAKURA_SLAB, ModBlocks.SAKURA_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
        registerSideSlab(ModSideSlabs.SIDE_STONE_SLAB, Blocks.STONE);
        simpleBlock(ModBlocks.SILVER_ORE);
        simpleBlock(ModBlocks.SUSHI_MAKER);
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
            Direction facing = blockState.get(AbstractButtonBlock.HORIZONTAL_FACING);
            AttachFace face = blockState.get(AbstractButtonBlock.FACE);
            boolean powered = blockState.get(AbstractButtonBlock.POWERED);
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
                .modelFile(blockState.get(PressurePlateBlock.POWERED) ? pressurePlateDown : pressurePlateUp)
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
        ModelFile slabFile = models().slab(BlockItemUtils.name(block), full, full, full);
        getVariantBuilder(block)
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.EAST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(90).rotationY(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.WEST).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(270).rotationY(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.SOUTH).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(90).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.NORTH).addModels(ConfiguredModel.builder().modelFile(slabFile).rotationX(270).build())
                .partialState().with(SideSlabBlock.TYPE, SideSlabType.DOUBLE).addModels(new ConfiguredModel(models().getExistingFile(full)));
    }
}
