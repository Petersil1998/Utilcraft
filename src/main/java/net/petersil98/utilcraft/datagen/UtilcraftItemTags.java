package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.items.UtilcraftItems;
import net.petersil98.utilcraft.tags.UtilcraftTags;

public class UtilcraftItemTags extends ItemTagsProvider {

    public UtilcraftItemTags(DataGenerator generator, UtilcraftBlockTags blockTags, ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.m_206421_(BlockTags.LEAVES, ItemTags.LEAVES);
        this.m_206421_(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.m_206421_(BlockTags.PLANKS, ItemTags.PLANKS);
        this.m_206421_(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.m_206421_(BlockTags.SLABS, ItemTags.SLABS);
        this.m_206421_(BlockTags.STAIRS, ItemTags.STAIRS);
        this.m_206421_(BlockTags.WALLS, ItemTags.WALLS);
        this.m_206421_(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.m_206421_(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.m_206421_(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.m_206421_(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.m_206421_(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.m_206421_(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

        this.m_206421_(Tags.Blocks.ORES, Tags.Items.ORES);
        this.m_206421_(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.m_206421_(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);

        this.m_206421_(UtilcraftTags.BlockTags.SAKURA_LOGS, UtilcraftTags.ItemTags.SAKURA_LOGS);
        this.m_206421_(UtilcraftTags.BlockTags.SIDE_SLABS_BLOCKS, UtilcraftTags.ItemTags.SIDE_SLABS_BLOCKS);

        this.m_206424_(ItemTags.BEACON_PAYMENT_ITEMS).add(UtilcraftItems.ROSE_QUARTZ.get());
    }
}
