package net.petersil98.utilcraft.biomes;

import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.petersil98.utilcraft.biomes.features.ModBiomeFeatures;

public class GraveyardBiome extends ModBiome {
    public GraveyardBiome() {
        super((new Biome.Builder())
                .surfaceBuilder(new ConfiguredSurfaceBuilder(ModBiomeFeatures.GRAVEYARD, ModBiomeFeatures.SNOW))
                .precipitation(Biome.RainType.SNOW)
                .category(Biome.Category.ICY)
                .depth(7.5F)
                .scale(0.2F)
                .temperature(-0.25F)
                .downfall(0.3F)
                .func_235097_a_(
                        (new BiomeAmbience.Builder())
                                .setWaterColor(4159204)
                                .setWaterFogColor(329011)
                                .setFogColor(12638463)
                                .setAmbientSound(SoundEvents.ENTITY_PHANTOM_AMBIENT)
                                .build())
                .parent(null)
        );

        //addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, );
    }
}
