package net.petersil98.utilcraft.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.items.UtilcraftItems;

import java.util.function.Consumer;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;

public class UtilcraftAdvancements implements Consumer<Consumer<Advancement>> {
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.advancement().display(UtilcraftBlocks.SAKURA_LEAVES, new TranslatableComponent("advancements.utilcraft.root.title"), new TranslatableComponent("advancements.utilcraft.root.description"), new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/advancements/backgrounds/background.png"), FrameType.TASK, true, true, false).addCriterion("place_sakura_sapling", PlacedBlockTrigger.TriggerInstance.placedBlock(UtilcraftBlocks.SAKURA_SAPLING)).save(consumer, String.format("%s/root", Utilcraft.MOD_ID));
        Advancement.Builder.advancement().parent(root).display(UtilcraftBlocks.ROSE_QUARTZ_ORE, new TranslatableComponent("advancements.utilcraft.mine_rose_quartz.title"), new TranslatableComponent("advancements.utilcraft.mine_rose_quartz.description"), null, FrameType.TASK, true, true, false).addCriterion("mined_rose_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(UtilcraftItems.ROSE_QUARTZ)).save(consumer, String.format("%s/mine_rose_quartz", Utilcraft.MOD_ID));
    }
}
