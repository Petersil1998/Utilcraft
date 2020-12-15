package net.petersil98.utilcraft.tags;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.petersil98.utilcraft.Main;

public class ModTags {
    public static ITag<Block> SIDE_SLABS_BLOCKS = BlockTags.makeWrapperTag(String.format("%s:side_slabs", Main.MOD_ID));
}
