package net.petersil98.utilcraft.dimensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.StructureManager;

import javax.annotation.Nonnull;

public class AfterlifeChunkGenerator extends ChunkGenerator {

    public AfterlifeChunkGenerator(BiomeProvider biomeProvider){
        super(biomeProvider, DimensionSettings.bootstrap().structureSettings());
    }

    public static final Codec<AfterlifeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source")
                            .forGetter((generator) -> generator.biomeSource)
            ).apply(instance, instance.stable(AfterlifeChunkGenerator::new))
    );

    @Nonnull
    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public ChunkGenerator withSeed(long p_230349_1_) {
        return this;
    }

    @Override
    public void buildSurfaceAndBedrock(@Nonnull WorldGenRegion p_225551_1_, @Nonnull IChunk p_225551_2_) {

    }

    @Override
    public void fillFromNoise(@Nonnull IWorld p_230352_1_, @Nonnull StructureManager p_230352_2_, @Nonnull IChunk p_230352_3_) {

    }

    @Override
    public int getBaseHeight(int x, int z, @Nonnull Heightmap.Type heightmapType) {
        return 0;
    }

    @Nonnull
    @Override
    public IBlockReader getBaseColumn(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }
}
