package net.petersil98.utilcraft.biomes.features;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.ModBlocks;

import java.util.OptionalInt;

public class ModBiomeFeatures {
    private static final BlockState SAKURA_LEAVES = ModBlocks.SAKURA_LEAVES.getDefaultState();
    private static final BlockState SAKURA_LOG = ModBlocks.SAKURA_LOG.getDefaultState();
    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig FANCY_SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();

    //public static final

    public static final SurfaceBuilder<SurfaceBuilderConfig> GRAVEYARD = new GraveyardBiomeFeature(SurfaceBuilderConfig.field_237203_a_);
    public static final SurfaceBuilderConfig SNOW = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.GRAVEL.getDefaultState());
}
