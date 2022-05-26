package net.petersil98.utilcraft.worldgen;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

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
        biome.m_204193_(GenerationStep.Decoration.UNDERGROUND_ORES.ordinal(), Holder.m_205709_(UtilcraftFeatures.SILVER_ORE_FEATURE.get()));
    }

    public static void addRoseQuartzOre(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.m_204193_(GenerationStep.Decoration.UNDERGROUND_ORES.ordinal(), Holder.m_205709_(UtilcraftFeatures.ROSE_QUARTZ_ORE_FEATURE.get()));
    }

    public static void addSakuraTrees(@Nonnull BiomeGenerationSettings.Builder biome) {
        biome.m_204193_(GenerationStep.Decoration.VEGETAL_DECORATION.ordinal(), Holder.m_205709_(UtilcraftFeatures.SAKURA_TREE_FEATURE.get()));
    }
}
