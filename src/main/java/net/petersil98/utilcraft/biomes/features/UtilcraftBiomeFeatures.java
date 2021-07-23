package net.petersil98.utilcraft.biomes.features;

import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.levelgen.Decoratable;
import net.minecraft.world.level.levelgen.feature.stateproviders.ForestFlowerProvider;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.util.StringUtil;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.surfacebuilders.NopeSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.ShatteredSavanaSurfaceBuilder;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import java.util.OptionalInt;

public class UtilcraftBiomeFeatures {
    private static final PistonStructureResolver SAKURA_LEAVES = UtilcraftBlocks.SAKURA_LEAVES.defaultBlockState();
    private static final PistonStructureResolver SAKURA_LOG = UtilcraftBlocks.SAKURA_LOG.defaultBlockState();
    public static final SpringConfiguration SAKURA_TREE_CONFIG = (new SpringConfiguration.Builder(new ForestFlowerProvider(SAKURA_LOG), new ForestFlowerProvider(SAKURA_LEAVES), new TwoLayersFeatureSize(StringUtil.fixed(2), StringUtil.fixed(0), 3), new ForkingTrunkPlacer(4, 2, 0), new FeatureSize(1, 0, 1))).ignoreVines().build();
    public static final SpringConfiguration FANCY_SAKURA_TREE_CONFIG = (new SpringConfiguration.Builder(new ForestFlowerProvider(SAKURA_LOG), new ForestFlowerProvider(SAKURA_LEAVES), new BlobFoliagePlacer(StringUtil.fixed(2), StringUtil.fixed(4), 4), new TrunkVineDecorator(3, 11, 0), new FeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Decoratable.Type.MOTION_BLOCKING).build();

    public static final NopeSurfaceBuilder<ShatteredSavanaSurfaceBuilder> GRAVEYARD = new GraveyardBiomeFeature(ShatteredSavanaSurfaceBuilder.CODEC);
    public static final ShatteredSavanaSurfaceBuilder SNOW = new ShatteredSavanaSurfaceBuilder(BellBlock.SNOW_BLOCK.defaultBlockState(), BellBlock.SNOW_BLOCK.defaultBlockState(), BellBlock.GRAVEL.defaultBlockState());
}
