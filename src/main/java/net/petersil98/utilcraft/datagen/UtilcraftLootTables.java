package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.items.UtilcraftItems;

public class UtilcraftLootTables extends BaseLootTableProvider {

    public UtilcraftLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        addSimpleBlock(UtilcraftBlocks.COMPRESSED_COBBLESTONE);
        addSimpleBlock(UtilcraftBlocks.DISENCHANTMENT_TABLE);
        addSimpleBlock(UtilcraftBlocks.GOLD_BRICK);
        addSlab(UtilcraftBlocks.GOLD_SLAB);
        addSimpleBlock(UtilcraftBlocks.GOLD_STAIRS);
        addSimpleBlock(UtilcraftBlocks.GOLD_WALL);
        addSlab(UtilcraftBlocks.REDSTONE_SLAB);
        addSimpleBlock(UtilcraftBlocks.REDSTONE_STAIRS);
        addSimpleBlock(UtilcraftBlocks.ROSE_QUARTZ_BLOCK);
        addOreBlock(UtilcraftBlocks.ROSE_QUARTZ_ORE, UtilcraftItems.ROSE_QUARTZ);
        addSimpleBlock(UtilcraftBlocks.SAKURA_BUTTON);
        addDoor(UtilcraftBlocks.SAKURA_DOOR);
        addSimpleBlock(UtilcraftBlocks.SAKURA_FENCE);
        addSimpleBlock(UtilcraftBlocks.SAKURA_FENCE_GATE);
        addLeave(UtilcraftBlocks.SAKURA_LEAVES, UtilcraftBlocks.SAKURA_SAPLING,true, true);
        addSimpleBlock(UtilcraftBlocks.SAKURA_LOG);
        addSimpleBlock(UtilcraftBlocks.SAKURA_PLANKS);
        addSimpleBlock(UtilcraftBlocks.SAKURA_PRESSURE_PLATE);
        addSimpleBlock(UtilcraftBlocks.SAKURA_SAPLING);
        addSimpleBlock(UtilcraftBlocks.SAKURA_SIGN);
        addSlab(UtilcraftBlocks.SAKURA_SLAB);
        addSimpleBlock(UtilcraftBlocks.SAKURA_STAIRS);
        addSimpleBlock(UtilcraftBlocks.SAKURA_TRAPDOOR);
        addSimpleBlockWithName(UtilcraftBlocks.SECURE_CHEST);
        addSideSlab(UtilcraftSideSlabs.SIDE_ACACIA_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_BIRCH_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_COBBLESTONE_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_DARK_OAK_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_GOLD_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_JUNGLE_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_OAK_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_REDSTONE_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_SAKURA_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_SPRUCE_SLAB);
        addSideSlab(UtilcraftSideSlabs.SIDE_STONE_SLAB);
        addSimpleBlock(UtilcraftBlocks.SILVER_ORE);
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
}
