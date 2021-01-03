package net.petersil98.utilcraft.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;
import net.petersil98.utilcraft.items.ModItems;

import java.util.function.Consumer;

public class ModAdvancements implements Consumer<Consumer<Advancement>> {
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.builder().withDisplay(ModBlocks.SAKURA_LEAVES, new TranslationTextComponent("advancements.utilcraft.root.title"), new TranslationTextComponent("advancements.utilcraft.root.description"), new ResourceLocation(Main.MOD_ID, "textures/gui/advancements/backgrounds/background.png"), FrameType.TASK, true, true, false).withCriterion("place_sakura_sapling", PlacedBlockTrigger.Instance.placedBlock(ModBlocks.SAKURA_SAPLING)).register(consumer, String.format("%s/root", Main.MOD_ID));
        Advancement.Builder.builder().withParent(root).withDisplay(ModBlocks.ROSE_QUARTZ_ORE, new TranslationTextComponent("advancements.utilcraft.mine_rose_quartz.title"), new TranslationTextComponent("advancements.utilcraft.mine_rose_quartz.description"), null, FrameType.TASK, true, true, false).withCriterion("mined_rose_quartz", InventoryChangeTrigger.Instance.forItems(ModItems.ROSE_QUARTZ)).register(consumer, String.format("%s/mine_rose_quartz", Main.MOD_ID));
    }
}
