package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.petersil98.utilcraft.blocks.custom.ModBiomeFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.SAKURA_TREE_CONFIG);
    }
}
