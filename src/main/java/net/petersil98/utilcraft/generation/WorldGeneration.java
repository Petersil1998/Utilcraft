package net.petersil98.utilcraft.generation;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.ForestFlowerProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.placement.EndGatewayPlacementDecorator;
import net.minecraft.world.level.levelgen.placement.EmeraldPlacementDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;

import net.minecraft.data.worldgen.Features;
import net.minecraft.util.StringUtil;
import net.minecraft.world.level.levelgen.feature.EndGatewayFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;

public class WorldGeneration {

    public static final List<ResourceLocation> SAKURA_SPAWN_BIOMES = Lists.newArrayList(
            BiomeSource.PLAINS.location(),
            BiomeSource.FOREST.location(),
            BiomeSource.FLOWER_FOREST.location(),
            BiomeSource.SUNFLOWER_PLAINS.location()
    );

    public static void addSilverOre(@Nonnull AmbientMoodSettings.Builder biome) {
        biome.addFeature(
                DebugLevelSource.Decoration.UNDERGROUND_ORES,
                EndGatewayFeature.ORE.configured(
                        new NoneDecoratorConfiguration(
                                NoneDecoratorConfiguration.FillerBlockType.NATURAL_STONE,
                                UtilcraftBlocks.SILVER_ORE.defaultBlockState(),
                                17
                        )
                ).range(128).squared()
        );
    }

    public static void addRoseQuartzOre(@Nonnull AmbientMoodSettings.Builder biome) {
        biome.addFeature(
                DebugLevelSource.Decoration.UNDERGROUND_ORES,
                EndGatewayFeature.ORE.configured(
                        new NoneDecoratorConfiguration(
                                NoneDecoratorConfiguration.FillerBlockType.NATURAL_STONE,
                                UtilcraftBlocks.ROSE_QUARTZ_ORE.defaultBlockState(),
                                4
                        )
                ).range(20).squared()
        );
    }

    public static void addSakuraTrees(@Nonnull AmbientMoodSettings.Builder biome) {
        biome.addFeature(
                DebugLevelSource.Decoration.VEGETAL_DECORATION,
                EndGatewayFeature.TREE
                        .configured(
                                new SpringConfiguration.Builder(
                                        new ForestFlowerProvider(UtilcraftBlocks.SAKURA_LOG.defaultBlockState()),
                                        new ForestFlowerProvider(UtilcraftBlocks.SAKURA_LEAVES.defaultBlockState()),
                                        new TwoLayersFeatureSize(StringUtil.fixed(2), StringUtil.fixed(0), 3),
                                        new ForkingTrunkPlacer(4, 2, 0),
                                        new FeatureSize(1, 0, 1))
                                        .ignoreVines()
                                        .build()
                        )
                        .decorated(Features.Decorators.HEIGHTMAP_SQUARE)
                        .decorated(EmeraldPlacementDecorator.COUNT_EXTRA.configured(new EndGatewayPlacementDecorator(1, 0.1F, 1)))
        );
    }
}
