package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;

public class ItemTags extends ItemTagsProvider {

    public ItemTags(DataGenerator generator, BlockTags blockTags, ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.copy(net.minecraft.tags.BlockTags.LEAVES, net.minecraft.tags.ItemTags.LEAVES);
        this.copy(net.minecraft.tags.BlockTags.LOGS, net.minecraft.tags.ItemTags.LOGS);
        this.copy(net.minecraft.tags.BlockTags.PLANKS, net.minecraft.tags.ItemTags.PLANKS);
        this.copy(net.minecraft.tags.BlockTags.SAPLINGS, net.minecraft.tags.ItemTags.SAPLINGS);
        this.copy(net.minecraft.tags.BlockTags.SLABS, net.minecraft.tags.ItemTags.SLABS);
        this.copy(net.minecraft.tags.BlockTags.STAIRS, net.minecraft.tags.ItemTags.STAIRS);
        this.copy(net.minecraft.tags.BlockTags.WALLS, net.minecraft.tags.ItemTags.WALLS);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_BUTTONS, net.minecraft.tags.ItemTags.WOODEN_BUTTONS);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_DOORS, net.minecraft.tags.ItemTags.WOODEN_DOORS);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_FENCES, net.minecraft.tags.ItemTags.WOODEN_FENCES);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_PRESSURE_PLATES, net.minecraft.tags.ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_SLABS, net.minecraft.tags.ItemTags.WOODEN_SLABS);
        this.copy(net.minecraft.tags.BlockTags.WOODEN_TRAPDOORS, net.minecraft.tags.ItemTags.WOODEN_TRAPDOORS);

        this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
    }
}
