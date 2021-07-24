package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(@Nonnull GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(new UtilcraftRecipes(generator));
        generator.addProvider(new UtilcraftLootTables(generator));
        UtilcraftBlockTags blockTags = new UtilcraftBlockTags(generator, existingFileHelper);
        generator.addProvider(blockTags);
        generator.addProvider(new UtilcraftItemTags(generator, blockTags, existingFileHelper));
        generator.addProvider(new UtilcraftGlobalLootModifiers(generator));
        generator.addProvider(new UtilcraftAdvancements(generator));
        generator.addProvider(UtilcraftLanguages.getEnglish(generator));
        generator.addProvider(UtilcraftLanguages.getGerman(generator));
        generator.addProvider(new UtilcraftBlockModels(generator, existingFileHelper));
        generator.addProvider(new UtilcraftItemModels(generator, existingFileHelper));
        generator.addProvider(new UtilcraftBlockStates(generator, existingFileHelper));
    }
}