package net.petersil98.utilcraft.utils;

import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.item.HoeItem;

import javax.annotation.Nonnull;

public class BlockItemUtils {

    @Nonnull
    public static String name(@Nonnull HoeItem item) {
        return item.getRegistryName().getPath();
    }

    @Nonnull
    public static String name(@Nonnull BeetrootBlock block) {
        return block.getRegistryName().getPath();
    }

    @Nonnull
    public static String namespace(@Nonnull HoeItem item) {
        return item.getRegistryName().getNamespace();
    }

    @Nonnull
    public static String namespace(@Nonnull BeetrootBlock block) {
        return block.getRegistryName().getNamespace();
    }
}
