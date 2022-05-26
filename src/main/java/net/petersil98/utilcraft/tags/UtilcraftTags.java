package net.petersil98.utilcraft.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {

    public static class BlockTags {
        public static TagKey<Block> SIDE_SLABS_BLOCKS = net.minecraft.tags.BlockTags.create(new ResourceLocation(Utilcraft.MOD_ID, "side_slabs"));
        public static TagKey<Block> SAKURA_LOGS = net.minecraft.tags.BlockTags.create(new ResourceLocation(Utilcraft.MOD_ID, "sakura_logs"));
        public static TagKey<Block> NEEDS_ROSE_QUARTZ_TOOL = net.minecraft.tags.BlockTags.create(new ResourceLocation(Utilcraft.MOD_ID, "needs_rose_quartz_tool"));
    }

    public static class ItemTags {
        public static TagKey<Item> SAKURA_LOGS = net.minecraft.tags.ItemTags.create(new ResourceLocation(Utilcraft.MOD_ID, "sakura_logs"));

        public static TagKey<Item> SIDE_SLABS_BLOCKS = net.minecraft.tags.ItemTags.create(new ResourceLocation(Utilcraft.MOD_ID, "side_slabs"));
    }
}
