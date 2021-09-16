package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
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
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

        this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);

        this.copy(UtilcraftTags.BlockTags.SAKURA_LOGS, UtilcraftTags.ItemTags.SAKURA_LOGS);

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(UtilcraftItems.ROSE_QUARTZ.get());
    }
}
