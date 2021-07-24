package net.petersil98.utilcraft.biomes.features;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import java.util.OptionalInt;

public class UtilcraftBiomeFeatures {
    private static final BlockState SAKURA_LEAVES = UtilcraftBlocks.SAKURA_LEAVES.defaultBlockState();
    private static final BlockState SAKURA_LOG = UtilcraftBlocks.SAKURA_LOG.defaultBlockState();
    private static final BlockState SAKURA_SAPLING = UtilcraftBlocks.SAKURA_SAPLING.defaultBlockState();
    public static final TreeConfiguration SAKURA_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(SAKURA_LOG), new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider(SAKURA_LEAVES), new SimpleStateProvider(SAKURA_SAPLING), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();
    public static final TreeConfiguration FANCY_SAKURA_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(SAKURA_LOG), new FancyTrunkPlacer(3, 11, 0), new SimpleStateProvider(SAKURA_LEAVES), new SimpleStateProvider(SAKURA_SAPLING), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build();
}
