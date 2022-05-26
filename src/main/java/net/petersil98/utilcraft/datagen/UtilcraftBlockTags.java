package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.tags.UtilcraftTags;

import static net.petersil98.utilcraft.blocks.UtilcraftBlocks.*;

public class UtilcraftBlockTags extends BlockTagsProvider {

    public UtilcraftBlockTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.m_206424_(UtilcraftTags.BlockTags.SIDE_SLABS_BLOCKS).add(SIDE_ACACIA_SLAB.get(), SIDE_BIRCH_SLAB.get(), SIDE_COBBLESTONE_SLAB.get(), SIDE_DARK_OAK_SLAB.get(), SIDE_GOLD_SLAB.get(), SIDE_JUNGLE_SLAB.get(), SIDE_OAK_SLAB.get(), SIDE_REDSTONE_SLAB.get(), SIDE_SAKURA_SLAB.get(), SIDE_SPRUCE_SLAB.get(), SIDE_STONE_SLAB.get());
        this.m_206424_(UtilcraftTags.BlockTags.SAKURA_LOGS).add(SAKURA_LOG.get());

        this.m_206424_(BlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE.get());
        this.m_206424_(BlockTags.FLOWER_POTS).add(POTTED_SAKURA_SAPLING.get());
        this.m_206424_(BlockTags.LEAVES).add(SAKURA_LEAVES.get());
        this.m_206424_(BlockTags.LOGS_THAT_BURN).m_206428_(UtilcraftTags.BlockTags.SAKURA_LOGS);
        this.m_206424_(BlockTags.PLANKS).add(SAKURA_PLANKS.get());
        this.m_206424_(BlockTags.SAPLINGS).add(SAKURA_SAPLING.get());
        this.m_206424_(BlockTags.SLABS).add(GOLD_SLAB.get(), REDSTONE_SLAB.get());
        this.m_206424_(BlockTags.STAIRS).add( GOLD_STAIRS.get(), REDSTONE_STAIRS.get(), GLASS_STAIRS.get());
        this.m_206424_(BlockTags.STANDING_SIGNS).add(SAKURA_SIGN.get());
        this.m_206424_(BlockTags.WALL_SIGNS).add(SAKURA_WALL_SIGN.get());
        this.m_206424_(BlockTags.WALLS).add(GOLD_WALL.get());
        this.m_206424_(BlockTags.WOODEN_BUTTONS).add(SAKURA_BUTTON.get());
        this.m_206424_(BlockTags.WOODEN_DOORS).add(SAKURA_DOOR.get());
        this.m_206424_(BlockTags.WOODEN_FENCES).add(SAKURA_FENCE.get());
        this.m_206424_(BlockTags.WOODEN_PRESSURE_PLATES).add(SAKURA_PRESSURE_PLATE.get());
        this.m_206424_(BlockTags.WOODEN_SLABS).add(SAKURA_SLAB.get());
        this.m_206424_(BlockTags.WOODEN_TRAPDOORS).add(SAKURA_TRAPDOOR.get());
        this.m_206424_(BlockTags.WOODEN_STAIRS).add(SAKURA_STAIRS.get());
        this.m_206424_(BlockTags.BEACON_BASE_BLOCKS).add(ROSE_QUARTZ_BLOCK.get());
        this.m_206424_(BlockTags.FEATURES_CANNOT_REPLACE).add(SECURE_CHEST.get());
        this.m_206424_(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add(SECURE_CHEST.get());

        this.m_206424_(BlockTags.MINEABLE_WITH_PICKAXE).add(GOLD_BRICK.get(), GOLD_STAIRS.get(), GOLD_SLAB.get(), GOLD_WALL.get(), COMPRESSED_COBBLESTONE.get(), SILVER_ORE.get(), SILVER_BLOCK.get(), ROSE_QUARTZ_ORE.get(), ROSE_QUARTZ_BLOCK.get(), SIDE_STONE_SLAB.get(), SIDE_COBBLESTONE_SLAB.get(), SIDE_GOLD_SLAB.get(), DISENCHANTMENT_TABLE.get(), REDSTONE_SLAB.get(), REDSTONE_STAIRS.get(), SIDE_REDSTONE_SLAB.get(), CHUNK_LOADER.get());
        this.m_206424_(BlockTags.MINEABLE_WITH_AXE).add(SIDE_OAK_SLAB.get(), SIDE_SPRUCE_SLAB.get(), SIDE_BIRCH_SLAB.get(), SIDE_JUNGLE_SLAB.get(), SIDE_ACACIA_SLAB.get(), SIDE_DARK_OAK_SLAB.get(), SIDE_SAKURA_SLAB.get(), SECURE_CHEST.get(), ENTROPY_TABLE.get());
        this.m_206424_(BlockTags.MINEABLE_WITH_HOE).add(SAKURA_LEAVES.get());

        this.m_206424_(BlockTags.NEEDS_STONE_TOOL).add(SILVER_BLOCK.get(), SILVER_ORE.get());
        this.m_206424_(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ROSE_QUARTZ_BLOCK.get(), ROSE_QUARTZ_ORE.get());

        this.m_206424_(Tags.Blocks.FENCE_GATES_WOODEN).add(SAKURA_FENCE_GATE.get());
        this.m_206424_(Tags.Blocks.FENCES_WOODEN).add(SAKURA_FENCE.get());
        this.m_206424_(Tags.Blocks.ORES).add(SILVER_ORE.get(), ROSE_QUARTZ_ORE.get());
        this.m_206424_(Tags.Blocks.CHESTS_WOODEN).add(SECURE_CHEST.get());
    }
}
