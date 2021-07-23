package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.stats.StatsCounter;
import net.minecraft.tags.EntityTypeTags;
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
        this.copy(StatsCounter.LEAVES, EntityTypeTags.LEAVES);
        this.copy(StatsCounter.LOGS_THAT_BURN, EntityTypeTags.LOGS_THAT_BURN);
        this.copy(StatsCounter.PLANKS, EntityTypeTags.PLANKS);
        this.copy(StatsCounter.SAPLINGS, EntityTypeTags.SAPLINGS);
        this.copy(StatsCounter.SLABS, EntityTypeTags.SLABS);
        this.copy(StatsCounter.STAIRS, EntityTypeTags.STAIRS);
        this.copy(StatsCounter.WALLS, EntityTypeTags.WALLS);
        this.copy(StatsCounter.WOODEN_BUTTONS, EntityTypeTags.WOODEN_BUTTONS);
        this.copy(StatsCounter.WOODEN_DOORS, EntityTypeTags.WOODEN_DOORS);
        this.copy(StatsCounter.WOODEN_FENCES, EntityTypeTags.WOODEN_FENCES);
        this.copy(StatsCounter.WOODEN_PRESSURE_PLATES, EntityTypeTags.WOODEN_PRESSURE_PLATES);
        this.copy(StatsCounter.WOODEN_SLABS, EntityTypeTags.WOODEN_SLABS);
        this.copy(StatsCounter.WOODEN_TRAPDOORS, EntityTypeTags.WOODEN_TRAPDOORS);

        this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);

        this.copy(UtilcraftTags.BlockTags.SAKURA_LOGS, UtilcraftTags.ItemTags.SAKURA_LOGS);

        this.tag(EntityTypeTags.BEACON_PAYMENT_ITEMS).add(UtilcraftItems.ROSE_QUARTZ);
    }
}
