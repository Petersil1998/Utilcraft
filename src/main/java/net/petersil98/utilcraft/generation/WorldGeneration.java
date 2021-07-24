package net.petersil98.utilcraft.generation;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.petersil98.utilcraft.biomes.features.UtilcraftBiomeFeatures;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;

import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;

public class WorldGeneration {

    public static final List<ResourceLocation> SAKURA_SPAWN_BIOMES = Lists.newArrayList(
            Biomes.PLAINS.location(),
            Biomes.FOREST.location(),
            Biomes.FLOWER_FOREST.location(),
            Biomes.SUNFLOWER_PLAINS.location()
    );

    public static void addSilverOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreConfiguration(
                                OreConfiguration.Predicates.NATURAL_STONE,
                                UtilcraftBlocks.SILVER_ORE.defaultBlockState(),
                                17
                        )
                ).range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(128)))).squared()
        );
    }

    public static void addRoseQuartzOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreConfiguration(
                                OreConfiguration.Predicates.NATURAL_STONE,
                                UtilcraftBlocks.ROSE_QUARTZ_ORE.defaultBlockState(),
                                4
                        )
                ).range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(20)))).squared()
        );
    }

    public static void addSakuraTrees(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                Feature.TREE
                        .configured(UtilcraftBiomeFeatures.SAKURA_TREE_CONFIG)
                        .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                        .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1F, 1)))
        );
    }
}
