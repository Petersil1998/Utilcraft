package net.petersil98.utilcraft.generation;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

public class WorldGeneration {

    public static void addSilverOre(BiomeGenerationSettings.Builder biome) {
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

    public static void addRoseQuartzOre(BiomeGenerationSettings.Builder biome) {
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

    public static void addSakuraTrees(BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.TREE.withConfiguration(
                        new BaseTreeFeatureConfig.Builder(
                                new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LOG.getDefaultState()),
                                new SimpleBlockStateProvider(UtilcraftBlocks.SAKURA_LEAVES.getDefaultState()),
                                new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                                new StraightTrunkPlacer(4, 2, 0),
                                new TwoLayerFeature(1, 0, 1))
                                .setIgnoreVines()
                                .build()
                )
        );
    }
}
