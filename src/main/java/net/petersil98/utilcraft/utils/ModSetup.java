package net.petersil98.utilcraft.utils;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.petersil98.utilcraft.blocks.ModBlocks;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("utilcraft") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.GOLDBRICK);
        }
    };

    public void init(){

    }
}
