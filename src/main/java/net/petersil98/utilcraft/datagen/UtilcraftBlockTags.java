package net.petersil98.utilcraft.datagen;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.tags.UtilcraftTags;

import static net.petersil98.utilcraft.blocks.sideslabs.UtilcraftSideSlabs.*;
import static net.petersil98.utilcraft.blocks.UtilcraftBlocks.*;

public class UtilcraftBlockTags extends BlockTagsProvider {

    public UtilcraftBlockTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(UtilcraftTags.BlockTags.SIDE_SLABS_BLOCKS).add(SIDE_ACACIA_SLAB, SIDE_BIRCH_SLAB, SIDE_COBBLESTONE_SLAB, SIDE_DARK_OAK_SLAB, SIDE_GOLD_SLAB, SIDE_JUNGLE_SLAB, SIDE_OAK_SLAB, SIDE_REDSTONE_SLAB, SIDE_SAKURA_SLAB, SIDE_SPRUCE_SLAB, SIDE_STONE_SLAB);
        this.tag(UtilcraftTags.BlockTags.SAKURA_LOGS).add(SAKURA_LOG);

        this.tag(BlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE);
        this.tag(BlockTags.FLOWER_POTS).add(POTTED_SAKURA_SAPLING);
        this.tag(BlockTags.LEAVES).add(SAKURA_LEAVES);
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(UtilcraftTags.BlockTags.SAKURA_LOGS);
        this.tag(BlockTags.PLANKS).add(SAKURA_PLANKS);
        this.tag(BlockTags.SAPLINGS).add(SAKURA_SAPLING);
        this.tag(BlockTags.SLABS).add(GOLD_SLAB, REDSTONE_SLAB);
        this.tag(BlockTags.STAIRS).add(SAKURA_STAIRS, GOLD_STAIRS, REDSTONE_STAIRS, GLASS_STAIRS);
        this.tag(BlockTags.STANDING_SIGNS).add(SAKURA_SIGN);
        this.tag(BlockTags.WALL_SIGNS).add(SAKURA_WALL_SIGN);
        this.tag(BlockTags.WALLS).add(GOLD_WALL);
        this.tag(BlockTags.WOODEN_BUTTONS).add(SAKURA_BUTTON);
        this.tag(BlockTags.WOODEN_DOORS).add(SAKURA_DOOR);
        this.tag(BlockTags.WOODEN_FENCES).add(SAKURA_FENCE);
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(SAKURA_PRESSURE_PLATE);
        this.tag(BlockTags.WOODEN_SLABS).add(SAKURA_SLAB);
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(SAKURA_TRAPDOOR);
        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(ROSE_QUARTZ_BLOCK);

        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(SAKURA_FENCE_GATE);
        this.tag(Tags.Blocks.FENCES_WOODEN).add(SAKURA_FENCE);
        this.tag(Tags.Blocks.ORES).add(SILVER_ORE, ROSE_QUARTZ_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COAL_ORE,
                Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
                Blocks.DEEPSLATE_LAPIS_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE_DIAMOND_ORE);
    }
}
