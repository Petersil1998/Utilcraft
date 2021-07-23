package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.petersil98.utilcraft.biomes.features.UtilcraftBiomeFeatures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(@Nonnull Random random, boolean p_225546_2_) {
        return Feature.TREE.configured(UtilcraftBiomeFeatures.SAKURA_TREE_CONFIG);
    }
}
