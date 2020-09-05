package net.petersil98.utilcraft.generation;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.petersil98.utilcraft.biomes.features.ModBiomeFeatures;
import net.petersil98.utilcraft.blocks.ModBlocks;

public class WorldGeneration {

    public static void addSilverOre(Biome biome) {
        biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                ModBlocks.SILVERORE.getDefaultState(),
                                17
                        )
                ).withPlacement(
                        Placement.COUNT_RANGE.configure(
                                new CountRangeConfig(20, 0, 0, 128)
                        )
                )
        );
    }

    public static void addRoseQuartzOre(Biome biome) {
        biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                ModBlocks.ROSEQUARTZORE.getDefaultState(),
                                4
                        )
                ).withPlacement(
                        Placement.COUNT_RANGE.configure(
                                new CountRangeConfig(5, 10, 0, 20)
                        )
                )
        );
    }

    public static void addSakuraTrees(Biome biome) {
        biome.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.RANDOM_SELECTOR.withConfiguration(
                        new MultipleRandomFeatureConfig(
                                ImmutableList.of(Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.FANCY_SAKURA_TREE_CONFIG).withChance(0.1F)),
                                Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.SAKURA_TREE_CONFIG)))
                        .withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1)
                        )
                )
        );
    }
}
