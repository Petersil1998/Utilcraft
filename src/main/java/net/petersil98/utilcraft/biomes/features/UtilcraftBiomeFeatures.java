package net.petersil98.utilcraft.biomes.features;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import java.util.OptionalInt;

public class UtilcraftBiomeFeatures {
    private static final BlockState SAKURA_LEAVES = UtilcraftBlocks.SAKURA_LEAVES.defaultBlockState();
    private static final BlockState SAKURA_LOG = UtilcraftBlocks.SAKURA_LOG.defaultBlockState();
    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build();
    public static final BaseTreeFeatureConfig FANCY_SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();
}
