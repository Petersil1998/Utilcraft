package net.petersil98.utilcraft.biomes.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.petersil98.utilcraft.blocks.ModBlocks;

import javax.annotation.Nonnull;
import java.util.Random;

public class GravestoneFeature extends Feature<NoFeatureConfig> {

    public GravestoneFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean generate(@Nonnull ISeedReader world, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull NoFeatureConfig config) {
        int i = 0;

        for(int j = 0; j < 128; ++j)
        {
            BlockState state = ModBlocks.GOLD_BRICK.getDefaultState();
            BlockPos blockpos = pos.add(random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4));

            if (world.isAirBlock(blockpos))
            {
                world.setBlockState(blockpos, state, 2);

                ++i;
            }
        }

        return i > 10;
    }
}
