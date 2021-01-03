package net.petersil98.utilcraft.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockItemUtils {

    public static String name(Item item) {
        return item.getRegistryName().getPath();
    }

    public static String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public static String namespace(Item item) {
        return item.getRegistryName().getNamespace();
    }

    public static String namespace(Block block) {
        return block.getRegistryName().getNamespace();
    }
}
