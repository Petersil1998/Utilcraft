package net.petersil98.utilcraft.generation;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldGeneration {

    public static List<ResourceLocation> sakuraSpawnBiomes = Lists.newArrayList(
            Biomes.PLAINS.location(),
            Biomes.FOREST.location(),
            Biomes.FLOWER_FOREST.location(),
            Biomes.SUNFLOWER_PLAINS.location()
    );

    public static void addSilverOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                UtilcraftBlocks.SILVER_ORE.defaultBlockState(),
                                17
                        )
                ).range(128).squared()
        );
    }

    public static void addRoseQuartzOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                UtilcraftBlocks.ROSE_QUARTZ_ORE.defaultBlockState(),
                                4
                        )
                ).range(20).squared()
        );
    }

    public static void addSakuraTrees(@Nonnull BiomeGenerationSettings.Builder biome, @Nonnull ResourceLocation biomeName) {
        if(sakuraSpawnBiomes.contains(biomeName)) {
            biome.addFeature(
                    GenerationStage.Decoration.VEGETAL_DECORATION,
                    Feature.TREE
                            .configured(
                                    new BaseTreeFeatureConfig.Builder(
                                            new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LOG.defaultBlockState()),
                                            new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LEAVES.defaultBlockState()),
                                            new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                                            new StraightTrunkPlacer(4, 2, 0),
                                            new TwoLayerFeature(1, 0, 1))
                                            .ignoreVines()
                                            .build()
                            )
                            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                            .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1)))
            );
        }
    }
}
