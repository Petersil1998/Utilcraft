package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.items.UtilcraftItems;

public class UtilcraftLootTables extends BaseLootTableProvider {

    public UtilcraftLootTables(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void addTables() {
        addSimpleBlock(UtilcraftBlocks.COMPRESSED_COBBLESTONE.get());
        addSimpleBlock(UtilcraftBlocks.DISENCHANTMENT_TABLE.get());
        addSimpleBlock(UtilcraftBlocks.GOLD_BRICK.get());
        addSlab(UtilcraftBlocks.GOLD_SLAB.get());
        addSimpleBlock(UtilcraftBlocks.GOLD_STAIRS.get());
        addSimpleBlock(UtilcraftBlocks.GOLD_WALL.get());
        addSlab(UtilcraftBlocks.REDSTONE_SLAB.get());
        addSimpleBlock(UtilcraftBlocks.REDSTONE_STAIRS.get());
        addSimpleBlock(UtilcraftBlocks.ROSE_QUARTZ_BLOCK.get());
        addOreBlock(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), UtilcraftItems.ROSE_QUARTZ.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_BUTTON.get());
        addDoor(UtilcraftBlocks.SAKURA_DOOR.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_FENCE.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_FENCE_GATE.get());
        addLeave(UtilcraftBlocks.SAKURA_LEAVES.get(), UtilcraftBlocks.SAKURA_SAPLING.get(),true, true);
        addSimpleBlock(UtilcraftBlocks.SAKURA_LOG.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_PLANKS.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_PRESSURE_PLATE.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_SAPLING.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_SIGN.get());
        addSlab(UtilcraftBlocks.SAKURA_SLAB.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_STAIRS.get());
        addSimpleBlock(UtilcraftBlocks.SAKURA_TRAPDOOR.get());
        addSimpleBlockWithName(UtilcraftBlocks.SECURE_CHEST.get());
        addSideSlab(UtilcraftBlocks.SIDE_ACACIA_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_BIRCH_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_COBBLESTONE_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_DARK_OAK_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_GOLD_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_JUNGLE_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_OAK_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_REDSTONE_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_SAKURA_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_SPRUCE_SLAB.get());
        addSideSlab(UtilcraftBlocks.SIDE_STONE_SLAB.get());
        addSimpleBlock(UtilcraftBlocks.SILVER_ORE.get());
        addPottedFlower(UtilcraftBlocks.POTTED_SAKURA_SAPLING.get(), UtilcraftBlocks.SAKURA_SAPLING.get());
        addSpawnerLootTable();
        addSilkTouchBlock(UtilcraftBlocks.GLASS_STAIRS.get());
    }

    private void addSimpleBlock(Block block) {
        lootTables.put(block, createSimpleTable(block));
    }

    private void addSimpleBlockWithName(Block block) {
        lootTables.put(block, createSimpleTableWithName(block));
    }

    private void addSlab(SlabBlock block) {
        lootTables.put(block, createSlabTable(block));
    }

    private void addSideSlab(SideSlabBlock block) {
        lootTables.put(block, createSideSlabTable(block));
    }

    private void addOreBlock(Block block, Item drop) {
        lootTables.put(block, createOreTable(block, drop));
    }

    private void addDoor(DoorBlock block) {
        lootTables.put(block, createDoorTable(block));
    }

    private void addLeave(LeavesBlock block, SaplingBlock sapling, boolean dropSticks, boolean dropApples) {
        lootTables.put(block, createLeaveTable(block, sapling, dropSticks, dropApples));
    }

    private void addPottedFlower(FlowerPotBlock potBlock, Block flowerBlock) {
        lootTables.put(potBlock, createPottedFlower((FlowerPotBlock) Blocks.FLOWER_POT, flowerBlock));
    }

    private void addSpawnerLootTable() {
        lootTables.put(Blocks.SPAWNER, createSpawnerLootTable());
    }

    private void addSilkTouchBlock(Block block) {
        lootTables.put(block, createSilkTouchBlock(block));
    }
}
