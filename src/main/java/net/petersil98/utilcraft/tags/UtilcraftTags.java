package net.petersil98.utilcraft.tags;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {
    public static ITag.INamedTag<Block> SIDE_SLABS_BLOCKS = BlockTags.makeWrapperTag(String.format("%s:side_slabs", Utilcraft.MOD_ID));
}
