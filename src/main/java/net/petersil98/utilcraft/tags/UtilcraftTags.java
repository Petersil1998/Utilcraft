package net.petersil98.utilcraft.tags;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.item.HoeItem;
import net.minecraft.tags.StaticTagHelper;
import net.petersil98.utilcraft.Utilcraft;

public class UtilcraftTags {

    public static class BlockTags {
        public static StaticTagHelper.INamedTag<BeetrootBlock> SIDE_SLABS_BLOCKS = net.minecraft.stats.StatsCounter.bind(String.format("%s:side_slabs", Utilcraft.MOD_ID));
        public static StaticTagHelper.INamedTag<BeetrootBlock> SAKURA_LOGS = net.minecraft.stats.StatsCounter.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }

    public static class ItemTags {
        public static StaticTagHelper.INamedTag<HoeItem> SAKURA_LOGS = net.minecraft.tags.EntityTypeTags.bind(String.format("%s:sakura_logs", Utilcraft.MOD_ID));
    }
}
