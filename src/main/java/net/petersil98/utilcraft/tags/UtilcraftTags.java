package net.petersil98.utilcraft.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {

    public static class BlockTags {
        public static Tag.Named<Block> SIDE_SLABS_BLOCKS = net.minecraft.tags.BlockTags.createOptional(new ResourceLocation(Utilcraft.MOD_ID, "side_slabs"));
        public static Tag.Named<Block> SAKURA_LOGS = net.minecraft.tags.BlockTags.createOptional(new ResourceLocation(Utilcraft.MOD_ID, "sakura_logs"));
        public static Tag.Named<Block> NEEDS_ROSE_QUARTZ_TOOL = net.minecraft.tags.BlockTags.createOptional(new ResourceLocation(Utilcraft.MOD_ID, "needs_rose_quartz_tool"));
    }

    public static class ItemTags {
        public static Tag.Named<Item> SAKURA_LOGS = net.minecraft.tags.ItemTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }
}
