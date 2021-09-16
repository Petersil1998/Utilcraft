package net.petersil98.utilcraft.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
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
        this.tag(UtilcraftTags.BlockTags.SIDE_SLABS_BLOCKS).add(SIDE_ACACIA_SLAB.get(), SIDE_BIRCH_SLAB.get(), SIDE_COBBLESTONE_SLAB.get(), SIDE_DARK_OAK_SLAB.get(), SIDE_GOLD_SLAB.get(), SIDE_JUNGLE_SLAB.get(), SIDE_OAK_SLAB.get(), SIDE_REDSTONE_SLAB.get(), SIDE_SAKURA_SLAB.get(), SIDE_SPRUCE_SLAB.get(), SIDE_STONE_SLAB.get());
        this.tag(UtilcraftTags.BlockTags.SAKURA_LOGS).add(SAKURA_LOG.get());

        this.tag(BlockTags.FENCE_GATES).add(SAKURA_FENCE_GATE.get());
        this.tag(BlockTags.FLOWER_POTS).add(POTTED_SAKURA_SAPLING.get());
        this.tag(BlockTags.LEAVES).add(SAKURA_LEAVES.get());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(UtilcraftTags.BlockTags.SAKURA_LOGS);
        this.tag(BlockTags.PLANKS).add(SAKURA_PLANKS.get());
        this.tag(BlockTags.SAPLINGS).add(SAKURA_SAPLING.get());
        this.tag(BlockTags.SLABS).add(GOLD_SLAB.get(), REDSTONE_SLAB.get());
        this.tag(BlockTags.STAIRS).add(SAKURA_STAIRS.get(), GOLD_STAIRS.get(), REDSTONE_STAIRS.get(), GLASS_STAIRS.get());
        this.tag(BlockTags.STANDING_SIGNS).add(SAKURA_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(SAKURA_WALL_SIGN.get());
        this.tag(BlockTags.WALLS).add(GOLD_WALL.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(SAKURA_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(SAKURA_DOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(SAKURA_FENCE.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(SAKURA_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(SAKURA_SLAB.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(SAKURA_TRAPDOOR.get());
        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(ROSE_QUARTZ_BLOCK.get());

        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(SAKURA_FENCE_GATE.get());
        this.tag(Tags.Blocks.FENCES_WOODEN).add(SAKURA_FENCE.get());
        this.tag(Tags.Blocks.ORES).add(SILVER_ORE.get(), ROSE_QUARTZ_ORE.get());
    }
}
