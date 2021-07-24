package net.petersil98.utilcraft.tags;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {

    public static class BlockTags {
        public static Tag.Named<Block> SIDE_SLABS_BLOCKS = net.minecraft.tags.BlockTags.bind(String.format("%s:side_slabs", Utilcraft.MOD_ID));
        public static Tag.Named<Block> SAKURA_LOGS = net.minecraft.tags.BlockTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }

    public static class ItemTags {
        public static Tag.Named<Item> SAKURA_LOGS = net.minecraft.tags.ItemTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }
}
