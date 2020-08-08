package net.petersil98.utilcraft.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.ModBlocks;

import java.util.OptionalInt;

public class ModBiomeFeatures {
    private static final BlockState SAKURA_LEAVES = ModBlocks.SAKURALEAVES.getDefaultState();
    private static final BlockState SAKURA_LOG = ModBlocks.SAKURALOG.getDefaultState();
    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig FANCY_SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new FancyFoliagePlacer(2, 0, 4, 0, 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
}
