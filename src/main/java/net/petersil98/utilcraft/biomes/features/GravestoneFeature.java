package net.petersil98.utilcraft.biomes.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.Random;

public class GravestoneFeature extends Feature<NoFeatureConfig> {

    public GravestoneFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(@Nonnull ISeedReader world, @Nonnull ChunkGenerator chunkGenerator, @Nonnull Random random, @Nonnull BlockPos pos, @Nonnull NoFeatureConfig config) {
        int i = 0;

        for(int j = 0; j < 128; ++j)
        {
            BlockState state = UtilcraftBlocks.GOLD_BRICK.defaultBlockState();
            BlockPos blockpos = pos.offset(random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4));

            if (world.isEmptyBlock(blockpos))
            {
                world.setBlock(blockpos, state, 2);

                ++i;
            }
        }

        return i > 10;
    }
}
