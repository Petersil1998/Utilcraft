package net.petersil98.utilcraft.datagen;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.enchantments.BeheadingModifier;
import net.petersil98.utilcraft.enchantments.UtilcraftEnchantments;

public class UtilcraftGlobalLootModifiers extends GlobalLootModifierProvider {

    public UtilcraftGlobalLootModifiers(DataGenerator generator) {
        super(generator, Utilcraft.MOD_ID);
    }

    @Override
    protected void start() {
        add(UtilcraftEnchantments.BEHEADING_SERIALIZER.getRegistryName().getPath(), UtilcraftEnchantments.BEHEADING_SERIALIZER, new BeheadingModifier(new ILootCondition[]{
                MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(UtilcraftEnchantments.BEHEADING, MinMaxBounds.IntBound.atLeast(1)))).build()
        }));
    }
}