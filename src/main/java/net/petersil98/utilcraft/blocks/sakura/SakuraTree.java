package net.petersil98.utilcraft.blocks.sakura;

import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.BonusChestFeature;
import net.minecraft.world.level.levelgen.feature.EndGatewayFeature;
import net.petersil98.utilcraft.biomes.features.UtilcraftBiomeFeatures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends TrappedChestBlockEntity {
    @Nullable
    @Override
    protected BonusChestFeature<SpringConfiguration, ?> getConfiguredFeature(@Nonnull Random random, boolean p_225546_2_) {
        return EndGatewayFeature.TREE.configured(UtilcraftBiomeFeatures.SAKURA_TREE_CONFIG);
    }
}
