package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.StairsBlock;

public class SakuraStairs extends StairsBlock {

    public SakuraStairs(){
        super(() -> new SakuraPlanks().getDefaultState(), AbstractBlock.Properties.from(new SakuraPlanks()));
    }
}
