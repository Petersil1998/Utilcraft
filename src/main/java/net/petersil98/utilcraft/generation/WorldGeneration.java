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
            Biomes.PLAINS.getLocation(),
            Biomes.FOREST.getLocation(),
            Biomes.FLOWER_FOREST.getLocation(),
            Biomes.SUNFLOWER_PLAINS.getLocation()
    );

    public static void addSilverOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                UtilcraftBlocks.SILVER_ORE.getDefaultState(),
                                17
                        )
                ).range(128).square()
        );
    }

    public static void addRoseQuartzOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                UtilcraftBlocks.ROSE_QUARTZ_ORE.getDefaultState(),
                                4
                        )
                ).range(20).square()
        );
    }

    public static void addSakuraTrees(@Nonnull BiomeGenerationSettings.Builder biome, @Nonnull ResourceLocation biomeName) {
        if(sakuraSpawnBiomes.contains(biomeName)) {
            biome.withFeature(
                    GenerationStage.Decoration.VEGETAL_DECORATION,
                    Feature.TREE
                            .withConfiguration(
                                    new BaseTreeFeatureConfig.Builder(
                                            new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LOG.getDefaultState()),
                                            new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LEAVES.getDefaultState()),
                                            new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                                            new StraightTrunkPlacer(4, 2, 0),
                                            new TwoLayerFeature(1, 0, 1))
                                            .setIgnoreVines()
                                            .build()
                            )
                            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1)))
            );
        }
    }
}
