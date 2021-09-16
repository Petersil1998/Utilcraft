package net.petersil98.utilcraft.generation;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.petersil98.utilcraft.biomes.features.UtilcraftBiomeFeatures;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.List;

public class WorldGeneration {

    public static final List<ResourceLocation> SAKURA_SPAWN_BIOMES = Lists.newArrayList(
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
                                UtilcraftBlocks.SILVER_ORE.get().defaultBlockState(),
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
                                UtilcraftBlocks.ROSE_QUARTZ_ORE.get().defaultBlockState(),
                                4
                        )
                ).range(20).squared()
        );
    }

    public static void addSakuraTrees(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.TREE
                        .configured(UtilcraftBiomeFeatures.SAKURA_TREE_CONFIG)
                        .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                        .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1)))
        );
    }
}
