package net.petersil98.utilcraft.biomes.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import javax.annotation.Nonnull;
import java.util.Random;

public class GraveyardBiomeFeature extends SurfaceBuilder<SurfaceBuilderConfig> {

    public GraveyardBiomeFeature(Codec<SurfaceBuilderConfig> codec){
        super(codec);
    }

    @Override
    public void buildSurface(@Nonnull Random random, @Nonnull IChunk chunk, @Nonnull Biome biome, int x, int z, int startHeight, double noise, @Nonnull BlockState defaultBlock, @Nonnull BlockState defaultFluid, int seaLevel, long seed, @Nonnull SurfaceBuilderConfig config) {
        this.buildRealSurface(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTop(), config.getUnder(), config.getUnderWaterMaterial(), seaLevel);
    }

    protected void buildRealSurface(@Nonnull Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel){
        BlockState blockstate = top;
        BlockState blockstate1 = middle;
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        int i = -1;
        int j = (int)(noise / 3.0D + 8.0D + random.nextDouble() * 0.25D);
        int k = x & 15;
        int l = z & 15;

        for(int i1 = startHeight; i1 >= 0; --i1) {
            blockPos.setPos(k, i1, l);
            BlockState blockstate2 = chunk.getBlockState(blockPos);
            if (blockstate2.isAir()) {
                i = -1;
            } else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        blockstate = Blocks.AIR.getDefaultState();
                        blockstate1 = defaultBlock;
                    } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
                        blockstate = top;
                        blockstate1 = middle;
                    }

                    if (i1 < sealevel && (blockstate == null || blockstate.isAir())) {
                        if (biome.getTemperature(blockPos.setPos(x, i1, z)) < 0.15F) {
                            blockstate = Blocks.ICE.getDefaultState();
                        } else {
                            blockstate = defaultFluid;
                        }

                        blockPos.setPos(k, i1, l);
                    }

                    i = j;
                    if (i1 >= sealevel - 1) {
                        chunk.setBlockState(blockPos, blockstate, false);
                    } else if (i1 < sealevel - 7 - j) {
                        blockstate = Blocks.AIR.getDefaultState();
                        blockstate1 = defaultBlock;
                        chunk.setBlockState(blockPos, bottom, false);
                    } else {
                        chunk.setBlockState(blockPos, blockstate1, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunk.setBlockState(blockPos, blockstate1, false);
                    if (i == 0 && blockstate1.getBlock() == Blocks.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, i1 - 63);
                        blockstate1 = blockstate1.getBlock() == Blocks.RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                    }
                }
            }
        }
    }
}
