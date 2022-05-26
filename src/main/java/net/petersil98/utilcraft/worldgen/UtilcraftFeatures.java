package net.petersil98.utilcraft.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import java.util.List;

public class UtilcraftFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Utilcraft.MOD_ID);

    public static RegistryObject<ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>> ORE_SILVER = CONFIGURED_FEATURES.register("ore_silver",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                    buildStandardOreList(UtilcraftBlocks.SILVER_ORE.get(), UtilcraftBlocks.SILVER_ORE.get()), 17)));

    public static RegistryObject<ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>> ORE_ROSE_QUARTZ = CONFIGURED_FEATURES.register("ore_rose_quartz",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                    buildStandardOreList(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), UtilcraftBlocks.ROSE_QUARTZ_ORE.get()), 4)));
    public static RegistryObject<ConfiguredFeature<TreeConfiguration, Feature<TreeConfiguration>>> SAKURA_TREE = CONFIGURED_FEATURES.register("sakura_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, buildSakuraTreeConfiguration()));

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Utilcraft.MOD_ID);

    public static RegistryObject<PlacedFeature> SILVER_ORE_FEATURE = PLACED_FEATURES.register("silver_ore",
            () -> new PlacedFeature(Holder.m_205709_(ORE_SILVER.get()), commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static RegistryObject<PlacedFeature> ROSE_QUARTZ_ORE_FEATURE = PLACED_FEATURES.register("rose_quartz_ore",
            () -> new PlacedFeature(Holder.m_205709_(ORE_ROSE_QUARTZ.get()), rareOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(20)))));

    public static RegistryObject<PlacedFeature> SAKURA_TREE_FEATURE = PLACED_FEATURES.register("sakura_tree",
            () -> new PlacedFeature(Holder.m_205709_(SAKURA_TREE.get()), buildSakuraPlacementModifiers()));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    private static List<OreConfiguration.TargetBlockState> buildStandardOreList(Block stoneOre, Block deepslateOre) {
        return List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, stoneOre.defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, deepslateOre.defaultBlockState()));
    }

    public static List<PlacementModifier> buildSakuraPlacementModifiers() {
        return List.of(
                PlacementUtils.countExtra(0, 0.05F, 1),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(UtilcraftBlocks.SAKURA_SAPLING.get().defaultBlockState(),BlockPos.ZERO)));
    }

    public static TreeConfiguration buildSakuraTreeConfiguration() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UtilcraftBlocks.SAKURA_LOG.get()), new StraightTrunkPlacer(4, 2, 0),BlockStateProvider.simple(UtilcraftBlocks.SAKURA_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build();
    }
}
