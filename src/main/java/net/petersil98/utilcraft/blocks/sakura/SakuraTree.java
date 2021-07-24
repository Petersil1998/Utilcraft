package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.petersil98.utilcraft.biomes.features.UtilcraftBiomeFeatures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(@Nonnull Random random, boolean p_225546_2_) {
        return Feature.TREE.configured(UtilcraftBiomeFeatures.SAKURA_TREE_CONFIG);
    }
}
