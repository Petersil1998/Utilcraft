package net.petersil98.utilcraft.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.items.UtilcraftItems;

import java.util.function.Consumer;

public class UtilcraftAdvancements implements Consumer<Consumer<Advancement>> {
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.advancement().display(UtilcraftBlocks.SAKURA_LEAVES.get(), new TranslationTextComponent("advancements.utilcraft.root.title"), new TranslationTextComponent("advancements.utilcraft.root.description"), new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/advancements/backgrounds/background.png"), FrameType.TASK, true, true, false).addCriterion("place_sakura_sapling", PlacedBlockTrigger.Instance.placedBlock(UtilcraftBlocks.SAKURA_SAPLING.get())).save(consumer, String.format("%s/root", Utilcraft.MOD_ID));
        Advancement.Builder.advancement().parent(root).display(UtilcraftBlocks.ROSE_QUARTZ_ORE.get(), new TranslationTextComponent("advancements.utilcraft.mine_rose_quartz.title"), new TranslationTextComponent("advancements.utilcraft.mine_rose_quartz.description"), null, FrameType.TASK, true, true, false).addCriterion("mined_rose_quartz", InventoryChangeTrigger.Instance.hasItems(UtilcraftItems.ROSE_QUARTZ.get())).save(consumer, String.format("%s/mine_rose_quartz", Utilcraft.MOD_ID));
    }
}
