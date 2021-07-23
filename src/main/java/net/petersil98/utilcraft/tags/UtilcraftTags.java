package net.petersil98.utilcraft.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {

    public static class BlockTags {
        public static ITag.INamedTag<Block> SIDE_SLABS_BLOCKS = net.minecraft.tags.BlockTags.bind(String.format("%s:side_slabs", Utilcraft.MOD_ID));
        public static ITag.INamedTag<Block> SAKURA_LOGS = net.minecraft.tags.BlockTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }

    public static class ItemTags {
        public static ITag.INamedTag<Item> SAKURA_LOGS = net.minecraft.tags.ItemTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }
}
