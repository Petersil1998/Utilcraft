package net.petersil98.utilcraft.dimensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.border.BorderStatus;
import net.minecraft.world.gen.*;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;

import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.border.package-info;
import net.minecraft.world.level.levelgen.Decoratable;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AfterlifeChunkGenerator extends package-info {

    public AfterlifeChunkGenerator(Biome biomeProvider){
        super(biomeProvider, GenerationStep.bootstrap().structureSettings());
    }

    public static final Codec<AfterlifeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Biome.CODEC.fieldOf("biome_source")
                            .forGetter((generator) -> generator.biomeSource)
            ).apply(instance, instance.stable(AfterlifeChunkGenerator::new))
    );

    @Nonnull
    @Override
    protected Codec<? extends package-info> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public package-info withSeed(long p_230349_1_) {
        return this;
    }

    @Override
    public void buildSurfaceAndBedrock(@Nonnull WorldGenRegion p_225551_1_, @Nonnull BorderStatus p_225551_2_) {

    }

    @Override
    public void fillFromNoise(@Nonnull GrassColor p_230352_1_, @Nonnull ServerLevelAccessor p_230352_2_, @Nonnull BorderStatus p_230352_3_) {

    }

    @Override
    public int getBaseHeight(int x, int z, @Nonnull Decoratable.Type heightmapType) {
        return 0;
    }

    @Nonnull
    @Override
    public BaseSpawner getBaseColumn(int p_230348_1_, int p_230348_2_) {
        return new LevelWriter(new PistonStructureResolver[0]);
    }
}
