package net.petersil98.utilcraft.generation;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.petersil98.utilcraft.blocks.ModBlocks;

public class WorldGeneration {

    public static void addSilverOre(BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.field_241882_a,
                                ModBlocks.SILVERORE.getDefaultState(),
                                17
                        )
                ).func_242733_d(128).func_242728_a()
        );
    }

    public static void addRoseQuartzOre(BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.field_241882_a,
                                ModBlocks.ROSEQUARTZORE.getDefaultState(),
                                4
                        )
                ).func_242733_d(20).func_242728_a()
        );
    }

    public static void addSakuraTrees(BiomeGenerationSettings.Builder biome) {
        biome.withFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.TREE.withConfiguration(
                        (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.SAKURALOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.SAKURALEAVES.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build())
        );
    }
}
