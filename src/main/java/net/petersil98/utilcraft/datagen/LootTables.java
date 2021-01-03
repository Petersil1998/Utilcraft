package net.petersil98.utilcraft.datagen;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.blocks.sideslabs.ModSideSlabs;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;
import net.petersil98.utilcraft.items.ModItems;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        addSimpleBlock(ModBlocks.COMPRESSED_COBBLESTONE);
        addSimpleBlock(ModBlocks.DISENCHANTMENT_TABLE);
        addSimpleBlock(ModBlocks.GOLD_BRICK);
        addSlab(ModBlocks.GOLD_SLAB);
        addSimpleBlock(ModBlocks.GOLD_STAIRS);
        addSimpleBlock(ModBlocks.GOLD_WALL);
        addSlab(ModBlocks.REDSTONE_SLAB);
        addSimpleBlock(ModBlocks.REDSTONE_STAIRS);
        addSimpleBlock(ModBlocks.ROSE_QUARTZ_BLOCK);
        addOreBlock(ModBlocks.ROSE_QUARTZ_ORE, ModItems.ROSE_QUARTZ);
        addSimpleBlock(ModBlocks.SAKURA_BUTTON);
        addDoor(ModBlocks.SAKURA_DOOR);
        addSimpleBlock(ModBlocks.SAKURA_FENCE);
        addSimpleBlock(ModBlocks.SAKURA_FENCE_GATE);
        addLeave(ModBlocks.SAKURA_LEAVES, ModBlocks.SAKURA_SAPLING,true, true);
        addSimpleBlock(ModBlocks.SAKURA_LOG);
        addSimpleBlock(ModBlocks.SAKURA_PLANKS);
        addSimpleBlock(ModBlocks.SAKURA_PRESSURE_PLATE);
        addSimpleBlock(ModBlocks.SAKURA_SAPLING);
        addSimpleBlock(ModBlocks.SAKURA_SIGN);
        addSlab(ModBlocks.SAKURA_SLAB);
        addSimpleBlock(ModBlocks.SAKURA_STAIRS);
        addSimpleBlock(ModBlocks.SAKURA_TRAPDOOR);
        addSimpleBlockWithName(ModBlocks.SECURE_CHEST);
        addSideSlab(ModSideSlabs.SIDE_ACACIA_SLAB);
        addSideSlab(ModSideSlabs.SIDE_BIRCH_SLAB);
        addSideSlab(ModSideSlabs.SIDE_COBBLESTONE_SLAB);
        addSideSlab(ModSideSlabs.SIDE_DARK_OAK_SLAB);
        addSideSlab(ModSideSlabs.SIDE_GOLD_SLAB);
        addSideSlab(ModSideSlabs.SIDE_JUNGLE_SLAB);
        addSideSlab(ModSideSlabs.SIDE_OAK_SLAB);
        addSideSlab(ModSideSlabs.SIDE_REDSTONE_SLAB);
        addSideSlab(ModSideSlabs.SIDE_SAKURA_SLAB);
        addSideSlab(ModSideSlabs.SIDE_SPRUCE_SLAB);
        addSideSlab(ModSideSlabs.SIDE_STONE_SLAB);
        addSimpleBlock(ModBlocks.SILVER_ORE);
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
