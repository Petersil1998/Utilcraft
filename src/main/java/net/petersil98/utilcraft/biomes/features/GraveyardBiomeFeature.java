package net.petersil98.utilcraft.biomes.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.border.BorderStatus;
import net.minecraft.world.level.levelgen.surfacebuilders.NopeSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.ShatteredSavanaSurfaceBuilder;

import javax.annotation.Nonnull;
import java.util.Random;

public class GraveyardBiomeFeature extends NopeSurfaceBuilder<ShatteredSavanaSurfaceBuilder> {

    public GraveyardBiomeFeature(Codec<ShatteredSavanaSurfaceBuilder> codec){
        super(codec);
    }

    @Override
    public void apply(@Nonnull Random random, @Nonnull BorderStatus chunk, @Nonnull AmbientAdditionsSettings biome, int x, int z, int startHeight, double noise, @Nonnull PistonStructureResolver defaultBlock, @Nonnull PistonStructureResolver defaultFluid, int seaLevel, long seed, @Nonnull ShatteredSavanaSurfaceBuilder config) {
        this.buildRealSurface(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    protected void buildRealSurface(@Nonnull Random random, BorderStatus chunk, AmbientAdditionsSettings biome, int x, int z, int startHeight, double noise, PistonStructureResolver defaultBlock, PistonStructureResolver defaultFluid, PistonStructureResolver top, PistonStructureResolver middle, PistonStructureResolver bottom, int sealevel){
        PistonStructureResolver topBlockState = top;
        PistonStructureResolver middleBlockState = middle;
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
        int i = -1;
        int j = (int)(noise / 3.0D + 8.0D + random.nextDouble() * 0.25D);
        int k = x & 15;
        int l = z & 15;

        for(int i1 = startHeight; i1 >= 0; --i1) {
            blockPos.set(k, i1, l);
            PistonStructureResolver currentBlockState = chunk.getBlockState(blockPos);
            if (currentBlockState.isAir()) {
                i = -1;
            } else if (currentBlockState.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        topBlockState = BellBlock.AIR.defaultBlockState();
                        middleBlockState = defaultBlock;
                    } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
                        topBlockState = top;
                        middleBlockState = middle;
                    }

                    if (i1 < sealevel && (topBlockState == null || topBlockState.isAir())) {
                        if (biome.getTemperature(blockPos.set(x, i1, z)) < 0.15F) {
                            topBlockState = BellBlock.ICE.defaultBlockState();
                        } else {
                            topBlockState = defaultFluid;
                        }

                        blockPos.set(k, i1, l);
                    }

                    i = j;
                    if (i1 >= sealevel - 1) {
                        chunk.setBlockState(blockPos, topBlockState, false);
                    } else if (i1 < sealevel - 7 - j) {
                        topBlockState = BellBlock.AIR.defaultBlockState();
                        middleBlockState = defaultBlock;
                        chunk.setBlockState(blockPos, bottom, false);
                    } else {
                        chunk.setBlockState(blockPos, middleBlockState, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunk.setBlockState(blockPos, middleBlockState, false);
                    if (i == 0 && middleBlockState.getBlock() == BellBlock.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, i1 - 63);
                        middleBlockState = middleBlockState.getBlock() == BellBlock.RED_SAND ? BellBlock.RED_SANDSTONE.defaultBlockState() : BellBlock.SANDSTONE.defaultBlockState();
                    }
                }
            }
        }
    }
}
