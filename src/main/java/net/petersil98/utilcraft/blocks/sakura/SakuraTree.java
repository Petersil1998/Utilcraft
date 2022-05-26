package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.petersil98.utilcraft.worldgen.UtilcraftFeatures;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SakuraTree extends AbstractTreeGrower {

    @org.jetbrains.annotations.Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> m_203525_(@NotNull Random p_204307_, boolean p_204308_) {
        return FeatureUtils.m_206488_("sakura", Feature.TREE, UtilcraftFeatures.buildSakuraTreeConfiguration());
    }
}
